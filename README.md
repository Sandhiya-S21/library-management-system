# Library Management System

A console-based Library Management System written in Java. It lets a librarian add, search, issue, return, and delete books through a simple menu-driven interface.

Built to practice core Java and object-oriented programming concepts: encapsulation, classes and objects, collections, and input validation.

## Features

- **Add Book**: add a book with a unique ID, title, and author
- **View All Books**: list every book with its availability status
- **Search Book**: search by Book ID or by title (case-insensitive, partial match)
- **Issue Book**: issue an available book to a student
- **Return Book**: mark an issued book as returned
- **Delete Book**: remove a book from the library (issued books cannot be deleted)
- **View Issued Books**: see all currently issued books and who has them

## Input Validation

- Rejects non-numeric input where a number is expected
- Prevents duplicate Book IDs
- Rejects empty titles, author names, and student names
- Prevents issuing a book that is already issued
- Prevents returning a book that is not issued
- Prevents deleting a book that is currently issued

## Requirements

- Java 11 or later (developed and tested on Java 17)

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/library-management-system.git
   cd library-management-system
   ```
2. Compile:
   ```bash
   javac LibraryManagementSystem.java
   ```
3. Run:
   ```bash
   java LibraryManagementSystem
   ```

## Sample Output

```
========================================
      LIBRARY MANAGEMENT SYSTEM
========================================
1. Add Book
2. View All Books
3. Search Book
4. Issue Book
5. Return Book
6. Delete Book
7. View Issued Books
8. Exit
========================================
Enter your choice: 2

----- All Books -----
Book ID    Title                          Author                    Status
-----------------------------------------------------------------------------------
101        Java Programming               James Gosling             Available
102        Python Basics                  Guido van Rossum          Available
103        Database Systems               Raghu Ramakrishnan        Available
```

## Project Structure

```
library-management-system/
├── LibraryManagementSystem.java   # Main class with menu and operations, plus the Book class
├── README.md
└── .gitignore
```

## Concepts Used

- Object-oriented programming: encapsulation with private fields and public getters
- `ArrayList` for storing books
- `Scanner` for console input
- Exception handling (`NumberFormatException`) for safe input parsing
- Formatted output with `printf`

## Limitations and Future Improvements

- Data is stored in memory only, so books and issue records are lost when the program exits
- Planned improvements:
  - Persist data using file storage or a SQL database (JDBC)
  - Add issue and due dates with fine calculation
  - Split classes into separate files (`Book.java`, `Library.java`)
  - Add a GUI using JavaFX or Swing

