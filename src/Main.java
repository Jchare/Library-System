import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List All Books");
            System.out.println("6. Sort Books by Title");
            System.out.println("7. Sort Books by Year");
            System.out.println("8. Search by Title");
            System.out.println("9. Search by Author");
            System.out.println("10. Search by ISBN");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear input buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    Book book = new Book(title, author, isbn, year);
                    library.addBook(book);
                    break;

                case 2:
                    System.out.print("Enter title of book to delete: ");
                    title = sc.nextLine();
                    library.deleteBook(title);
                    break;

                case 3:
                    System.out.print("Enter title of book to borrow: ");
                    title = sc.nextLine();
                    library.borrowBook(title);
                    break;

                case 4:
                    System.out.print("Enter title of book to return: ");
                    title = sc.nextLine();
                    library.returnBook(title);
                    break;

                case 5:
                    library.listBooks();
                    break;

                case 6:
                    library.sortBooksByTitle();
                    break;

                case 7:
                    library.sortBooksByYear();
                    break;

                case 8:
                    System.out.print("Enter title to search: ");
                    title = sc.nextLine();
                    library.searchByTitle(title);
                    break;

                case 9:
                    System.out.print("Enter author to search: ");
                    author = sc.nextLine();
                    library.searchByAuthor(author);
                    break;

                case 10:
                    System.out.print("Enter ISBN to search: ");
                    isbn = sc.nextLine();
                    library.searchByISBN(isbn);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
