package com.nixsolutions.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String genreStorageType;

    private final List<Book> bookList;

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
}
