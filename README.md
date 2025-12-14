Library Management System (Java)

A simple in-memory Library Management System built in Java to demonstrate OOP, SOLID principles, Java Collections, logging, and common design patterns.
No database or external APIs are used.

Features
Book Management

Add, update, and remove books

Search books by:

Title

Author

ISBN

Patron Management

Add patrons and update patron information

Track patron borrowing history

Lending Process

Checkout books (mark as borrowed)

Return books (mark as available)

Track active loans

Inventory Management

Each book has a status: AVAILABLE, BORROWED, RESERVED

Optional Extension (if enabled in your code)

Reservation system with notifications (in-memory “email” logging)

Tech & Design
OOP Concepts Used

Encapsulation: private fields + getters/setters

Abstraction: repository interfaces

Polymorphism: search strategies implementing a common interface

SOLID Principles

S: Domain classes represent data; services handle business rules; repositories handle storage

O: New search strategies can be added without modifying existing search logic

L: Strategies are interchangeable through the interface

I: Separate repository interfaces (Book, Patron, Loan)

D: Services depend on repository interfaces (not concrete classes)

Design Patterns

Strategy Pattern: Book searching (Title/Author/ISBN strategies)

Factory Pattern: Selecting the correct search strategy

Observer Pattern (optional extension): Reservation notification (simulated email via logs)

Logging

Uses Java’s built-in java.util.logging to log important actions and errors.

Project Structure
src/main/java/com/lms/
  app/             # Main runner + logger
  domain/          # Entities (Book, Patron, Loan)
  repository/      # Repository interfaces + in-memory implementations
  service/         # Business logic services
    search/        # Search Strategy + Factory
  notification/    # Observer-based notification (optional)
  recommendation/  # (optional future work)

How to Run
1) Compile & Run (macOS/Linux)

From project root:

rm -rf out
mkdir -p out
find src/main/java -type f -name "*.java" -print0 | xargs -0 javac -d out
java -cp out com.lms.app.Main

2) Run in VS Code

Open the project folder

Open Main.java

Click Run above the main() method

Class Diagram (Mermaid)

GitHub renders Mermaid diagrams automatically in Markdown.

classDiagram
direction LR

class Book {
  -String isbn
  -String title
  -String author
  -int publicationYear
  -BookStatus status
  +getIsbn()
  +getTitle()
  +getAuthor()
  +getPublicationYear()
  +getStatus()
  +setTitle(String)
  +setAuthor(String)
  +setPublicationYear(int)
  +setStatus(BookStatus)
}

class Patron {
  -String patronId
  -String name
  -String email
  -List~String~ borrowedIsbnsHistory
  +getPatronId()
  +getName()
  +getEmail()
  +recordBorrowedIsbn(String)
}

class Loan {
  -String loanId
  -String isbn
  -String patronId
  -LocalDate checkoutDate
  -LocalDate returnDate
  +isActive()
  +markReturned(LocalDate)
}

class BookStatus {
  <<enum>>
  AVAILABLE
  BORROWED
  RESERVED
}

Book --> BookStatus

class BookRepository {
  <<interface>>
  +save(Book)
  +findByIsbn(String) Optional~Book~
  +findAll() List~Book~
  +deleteByIsbn(String)
}

class PatronRepository {
  <<interface>>
  +save(Patron)
  +findById(String) Optional~Patron~
  +findAll() List~Patron~
}

class LoanRepository {
  <<interface>>
  +save(Loan)
  +findActiveLoanByIsbn(String) Optional~Loan~
  +findLoansByPatron(String) List~Loan~
}

class InMemoryBookRepository
class InMemoryPatronRepository
class InMemoryLoanRepository

BookRepository <|.. InMemoryBookRepository
PatronRepository <|.. InMemoryPatronRepository
LoanRepository <|.. InMemoryLoanRepository

class BookService
class PatronService
class LendingService

BookService --> BookRepository
PatronService --> PatronRepository
LendingService --> BookRepository
LendingService --> PatronRepository
LendingService --> LoanRepository

class BookSearchStrategy {
  <<interface>>
  +search(List~Book~, String) List~Book~
}

class TitleSearchStrategy
class AuthorSearchStrategy
class IsbnSearchStrategy

BookSearchStrategy <|.. TitleSearchStrategy
BookSearchStrategy <|.. AuthorSearchStrategy
BookSearchStrategy <|.. IsbnSearchStrategy

class SearchStrategyFactory
SearchStrategyFactory --> BookSearchStrategy

%% Optional: Reservations + Observer
class ReservationService
ReservationService --> BookRepository
ReservationService --> PatronRepository

class NotificationObserver {
  <<interface>>
  +notify(String)
}

class EmailNotifier
NotificationObserver <|.. EmailNotifier
ReservationService --> NotificationObserver

Notes / Limitations

This project is in-memory only (no persistence).

Notifications are simulated using logs (no real email sending).
