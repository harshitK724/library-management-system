package com.lms.domain;

import java.util.Objects;

public class Book {

    private final String isbn;   // unique identifier
    private String title;
    private String author;
    private int publicationYear;
    private BookStatus status;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = Objects.requireNonNull(isbn);
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
        this.publicationYear = publicationYear;
        this.status = BookStatus.AVAILABLE;
    }

    // Getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public BookStatus getStatus() { return status; }

    // Setters
    public void setTitle(String title) { this.title = Objects.requireNonNull(title); }
    public void setAuthor(String author) { this.author = Objects.requireNonNull(author); }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
    public void setStatus(BookStatus status) { this.status = status; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book other = (Book) obj;
        return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + publicationYear +
                ", status=" + status +
                '}';
    }
}
