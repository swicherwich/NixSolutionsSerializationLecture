package com.nixsolutions.externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookReader extends Person {

    private static final long serialVersionUID = 1L;

    private int readerId;

    private List<Book> borrowedBooks;

    /**
     * Constructor for {@code java.io.Serializable}
     */
    public BookReader() {
        this(null, null, -1);
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(firstName);
        out.writeObject(lastName);
        out.writeInt(readerId);
        out.writeInt(borrowedBooks.size());

        for(Book book: borrowedBooks) {
            out.writeObject(book);
        }
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        this.firstName = (String) in.readObject();
        this.lastName = (String) in.readObject();
        this.readerId = in.readInt();
        this.borrowedBooks = new ArrayList<>();

        int bookListSize = in.readInt();
        for(int i = bookListSize; i > 0; --i) {
            borrowedBooks.add((Book) in.readObject());
        }
    }
}
