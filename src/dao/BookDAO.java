package dao;

import model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String FILE_NAME = "books.dat";

    public List<Book> getAllBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }
}