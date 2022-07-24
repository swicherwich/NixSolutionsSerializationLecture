package com.nixsolutions.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookStorage implements Externalizable {

    private static final long serialVersionUID = 1L;

    private String genreStorageType;

    private List<Book> bookList;

    /**
     * Constructor for {@code java.io.Serializable}
     */
    public BookStorage() {
    }

    /**
     * Constructor for storage for special book genre but with no books in it yet
     * @param genreStorage Genre of all the books in the storage
     */
    public BookStorage(String genreStorage) {
        this(genreStorage, new ArrayList<>());
    }

    /**
     * Fully initialized constructor with storage genre and books in it
     * @param genreStorage Genre of all the books in the storage
     * @param books Storage books with corresponding genre
     */
    public BookStorage(String genreStorage, List<Book> books) {
        this.genreStorageType = genreStorage;
        this.bookList = books;
    }

    public String getGenreStorageType() {
        return genreStorageType;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public boolean addBook(Book book) {
        return bookList.add(book);
    }

    public boolean addBooks(Collection<Book> books) {
        return bookList.addAll(books);
    }

    @Override
    public String toString() {
        StringBuilder books = new StringBuilder();
        bookList.forEach(book -> books.append("\t\t").append(book).append('\n'));
        return "Storage of " + genreStorageType.toLowerCase() + " genre contains:\n" + books;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(genreStorageType);
        out.writeInt(bookList.size());
        for(Book book: bookList) {
            out.writeObject(book);
        }
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        this.genreStorageType = (String) in.readObject();
        this.bookList = new ArrayList<>();

        int bookListSize = in.readInt();
        for(int i = bookListSize; i > 0; --i) {
            bookList.add((Book) in.readObject());
        }
    }
}
