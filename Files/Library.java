import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private Stack<Action> actionStack;
    private Queue<Book> borrowQueue;
    private static final String FILE_PATH = "books.txt";

    // Constructor
    public Library() {
        books = new ArrayList<>();
        actionStack = new Stack<>();
        borrowQueue = new LinkedList<>();
        loadBooksFromFile();   // Load existing books
    }

    // Add book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
        saveBooksToFile();
    }

    // Delete book
    public void deleteBook(String title) {
        Book book = findByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        books.remove(book);
        System.out.println("Book removed: " + book.getTitle());
        saveBooksToFile();
    }

    // Borrow book
    public void borrowBook(String title) {
        Book book = findByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isBorrowed()) {
            System.out.println("Book is already borrowed.");
            return;
        }
        book.setBorrowed(true);
        borrowQueue.add(book);
        actionStack.push(new Action("borrow", book));
        System.out.println("Borrowed: " + book.getTitle());
        saveBooksToFile();
    }

    // Return book
    public void returnBook(String title) {
        Book book = findByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!book.isBorrowed()) {
            System.out.println("That book was not borrowed.");
            return;
        }
        book.setBorrowed(false);
        actionStack.push(new Action("return", book));
        System.out.println("Returned: " + book.getTitle());
        saveBooksToFile();
    }

    // Find book by title
    public Book findByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    // List all books
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Save books to file
    public void saveBooksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," +
                               book.getAuthor() + "," +
                               book.getISBN() + "," +
                               book.getYear() + "," +
                               book.isBorrowed());
            }
            System.out.println("Books saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    // Load books from file
    public void loadBooksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No existing library file found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String title = parts[0];
                    String author = parts[1];
                    String isbn = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    boolean borrowed = Boolean.parseBoolean(parts[4]);

                    Book book = new Book(title, author, isbn, year);
                    book.setBorrowed(borrowed);
                    books.add(book);
                }
            }
            System.out.println("Books loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    // Sort books alphabetically by title
    public void sortBooksByTitle() {
        Collections.sort(books, Comparator.comparing(Book::getTitle));
        System.out.println("Books sorted by title.");
        saveBooksToFile();
    }

    // Sort books by year
    public void sortBooksByYear() {
        Collections.sort(books, Comparator.comparingInt(Book::getYear));
        System.out.println("Books sorted by year.");
        saveBooksToFile();
    }
}
