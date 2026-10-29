import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private final int bookId;
    private final String title;
    private final String author;
    private boolean issued;
    private String issuedTo;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.issued = false;
        this.issuedTo = "";
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void issueBook(String studentName) {
        issued = true;
        issuedTo = studentName;
    }

    public void returnBook() {
        issued = false;
        issuedTo = "";
    }

    public void displayBook() {
        System.out.printf("%-10d %-30s %-25s %-15s%n",
                bookId, title, author, issued ? "Issued" : "Available");
    }
}

public class LibraryManagementSystem {
    private static final ArrayList<Book> books = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static final String LINE = "-".repeat(83);

    public static void main(String[] args) {
        books.add(new Book(101, "Java Programming", "James Gosling"));
        books.add(new Book(102, "Python Basics", "Guido van Rossum"));
        books.add(new Book(103, "Database Systems", "Raghu Ramakrishnan"));

        int choice;
        do {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("      LIBRARY MANAGEMENT SYSTEM");
            System.out.println("=".repeat(40));
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Delete Book");
            System.out.println("7. View Issued Books");
            System.out.println("8. Exit");
            System.out.println("=".repeat(40));
            choice = readInt("Enter your choice:");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    viewIssuedBooks();
                    break;
                case 8:
                    System.out.println("Thank you for using Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number from 1 to 8.");
                    break;
            }
        } while (choice != 8);

        sc.close();
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message + " ");
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static String readLine(String message) {
        System.out.print(message + " ");
        return sc.nextLine().trim();
    }

    private static Book findBookById(int id) {
        for (Book book : books) {
            if (book.getBookId() == id) {
                return book;
            }
        }
        return null;
    }

    private static void addBook() {
        System.out.println("\n----- Add Book -----");
        int id = readInt("Enter Book ID:");
        if (findBookById(id) != null) {
            System.out.println("A book with this ID already exists!");
            return;
        }

        String title = readLine("Enter Book Title:");
        String author = readLine("Enter Author Name:");
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author name cannot be empty!");
            return;
        }

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        System.out.println("\n----- All Books -----");
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        printTableHeader();
        for (Book book : books) {
            book.displayBook();
        }
    }

    private static void printTableHeader() {
        System.out.printf("%-10s %-30s %-25s %-15s%n", "Book ID", "Title", "Author", "Status");
        System.out.println(LINE);
    }

    private static void searchBook() {
        System.out.println("\n----- Search Book -----");
        System.out.println("1. Search by Book ID");
        System.out.println("2. Search by Book Title (case-insensitive)");
        int option = readInt("Choose search method:");
        boolean found = false;

        if (option == 1) {
            int id = readInt("Enter Book ID:");
            Book book = findBookById(id);
            if (book != null) {
                printTableHeader();
                book.displayBook();
                found = true;
            }
        } else if (option == 2) {
            String title = readLine("Enter Book Title:");
            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    if (!found) {
                        printTableHeader();
                    }
                    book.displayBook();
                    found = true;
                }
            }
        } else {
            System.out.println("Invalid search option!");
            return;
        }

        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    private static void issueBook() {
        System.out.println("\n----- Issue Book -----");
        int id = readInt("Enter Book ID:");
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println("This book is already issued to " + book.getIssuedTo() + ".");
            return;
        }

        String studentName = readLine("Enter Student Name:");
        if (studentName.isEmpty()) {
            System.out.println("Student name cannot be empty!");
            return;
        }

        book.issueBook(studentName);
        System.out.println("Book issued successfully to " + studentName + "!");
    }

    private static void returnBook() {
        System.out.println("\n----- Return Book -----");
        int id = readInt("Enter Book ID:");
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (!book.isIssued()) {
            System.out.println("This book is not currently issued.");
            return;
        }

        String studentName = book.getIssuedTo();
        book.returnBook();
        System.out.println("Book returned successfully by " + studentName + "!");
    }

    private static void deleteBook() {
        System.out.println("\n----- Delete Book -----");
        int id = readInt("Enter Book ID:");
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Cannot delete a book that is currently issued!");
            return;
        }

        books.remove(book);
        System.out.println("Book deleted successfully!");
    }

    private static void viewIssuedBooks() {
        System.out.println("\n----- Issued Books -----");
        boolean found = false;

        for (Book book : books) {
            if (book.isIssued()) {
                if (!found) {
                    printTableHeader();
                    found = true;
                }
                book.displayBook();
                System.out.println("Issued To: " + book.getIssuedTo());
                System.out.println(LINE);
            }
        }

        if (!found) {
            System.out.println("There are no currently issued books.");
        }
    }
}