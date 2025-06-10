import service.LibraryService;
import util.FileUtil;
import model.Book;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileUtil.initializeFile();
        LibraryService service = new LibraryService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Show All Books (Sorted by Title)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN (format: 123-1234567890): ");
                    String isbn = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    if (service.addBook(isbn, title, author))
                        System.out.println("Book added successfully!");
                    else
                        System.out.println("Failed to add book. Check ISBN format or duplicate entry.");
                    break;
                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    if (service.removeBook(sc.nextLine()))
                        System.out.println("Book removed successfully!");
                    else
                        System.out.println("Book not found.");
                    break;
                case 3:
                    System.out.print("Enter title to search: ");
                    List<Book> found = service.searchByTitle(sc.nextLine());
                    if (found.isEmpty()) {
                        System.out.println("No books found with that title.");
                    } else {
                        found.forEach(System.out::println);
                    }
                    break;
                case 4:
                    List<Book> allBooks = service.getAllBooksSortedByTitle();
                    allBooks.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }
}