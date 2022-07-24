package com.nixsolutions.driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookReader extends Person {

    private static final long serialVersionUID = 1L;

    private final int readerId;

    private final List<Book> borrowedBooks;

    /**
     * Constructor for reader that has not taken any book
     *
     * @param readerId Unique reader identifier withing library
     */
    public BookReader(String firstName, String lastName, int readerId) {
        this(firstName, lastName, readerId, new ArrayList<>());
    }

    /**
     * Fully initialized constructor
     *
     * @param readerId      Unique reader identifier withing library
     * @param borrowedBooks All books that reader has taken in the library
     */
    public BookReader(String firstName, String lastName, int readerId,
            List<Book> borrowedBooks) {
        super(firstName, lastName);
        this.readerId = readerId;
        this.borrowedBooks = borrowedBooks;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getReaderId() {
        return readerId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean borrowBook(Book book) {
        return borrowedBooks.add(book);
    }

    public boolean borrowBooks(Collection<Book> books) {
        return borrowedBooks.addAll(books);
    }

    @Override
    public String toString() {
        if (borrowedBooks.isEmpty()) {
            return "Reader " + firstName + " " + lastName + " with reader id = "
                    + readerId + " has no borrowed books yet\n";
        }
        StringBuilder books = new StringBuilder();
        borrowedBooks.forEach(
                book -> books.append('\n')
                        .append("\t\t")
                        .append(book)
                        .append('\n'));
        return "Reader " + firstName + " " + lastName + " with reader id = "
                + readerId + " borrowed books: " + books;
    }
}
