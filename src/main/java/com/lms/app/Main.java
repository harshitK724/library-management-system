package com.lms.app;

import com.lms.domain.Book;
import com.lms.domain.Patron;
import com.lms.repository.BookRepository;
import com.lms.repository.InMemoryBookRepository;
import com.lms.repository.InMemoryLoanRepository;
import com.lms.repository.InMemoryPatronRepository;
import com.lms.repository.LoanRepository;
import com.lms.repository.PatronRepository;
import com.lms.service.BookService;
import com.lms.service.LendingService;
import com.lms.service.PatronService;
import com.lms.service.search.SearchStrategyFactory;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepo = new InMemoryBookRepository();
        PatronRepository patronRepo = new InMemoryPatronRepository();
        LoanRepository loanRepo = new InMemoryLoanRepository();

        BookService bookService = new BookService(bookRepo);
        PatronService patronService = new PatronService(patronRepo);
        LendingService lendingService = new LendingService(bookRepo, patronRepo, loanRepo);

        bookService.addBook(new Book("ISBN-1", "Clean Code", "Robert C. Martin", 2008));
        bookService.addBook(new Book("ISBN-2", "Effective Java", "Joshua Bloch", 2018));

        patronService.addPatron(new Patron("P-1", "Asha", "asha@example.com"));

        System.out.println(bookService.search(SearchStrategyFactory.SearchType.AUTHOR, "Bloch"));

        lendingService.checkout("ISBN-2", "P-1");
        lendingService.returnBook("ISBN-2");
    }
}
