package service;

import dao.BookDAO;
import model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class LibraryService {
    private BookDAO dao;
    private List<Book> books;

    public LibraryService() {
        dao = new BookDAO();
        books = dao.getAllBooks();
    }

    public boolean addBook(String isbn, String title, String author) {
        if (!isValidIsbn(isbn) || title.isBlank() || author.isBlank()) return false;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return false;
        }
        books.add(new Book(isbn, title, author));
        dao.saveBooks(books);
        return true;
    }

    public boolean removeBook(String isbn) {
        boolean removed = books.removeIf(book -> book.getIsbn().equals(isbn));
        if (removed) dao.saveBooks(books);
        return removed;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooksSortedByTitle() {
        books.sort(Comparator.comparing(Book::getTitle));
        return books;
    }

    private boolean isValidIsbn(String isbn) {
        return Pattern.matches("\\d{3}-\\d{10}", isbn);
    }
}