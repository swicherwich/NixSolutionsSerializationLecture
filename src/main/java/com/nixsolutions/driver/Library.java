package com.nixsolutions.driver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String libraryName;

    private transient List<BookStorage> bookStorageList;
    private transient List<BookReader> bookReaderList;

    /**
     * Library with no storages yet and readers
     */
    public Library(String libraryName) {
        this(libraryName, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Library with no readers yet
     *
     * @param bookStorageList List of library storages
     *                        with books of different genres
     */
    public Library(String libraryName, List<BookStorage> bookStorageList) {
        this(libraryName, bookStorageList, new ArrayList<>());
    }

    /**
     * Fully initialized library with storages and readers
     *
     * @param bookStorageList List of library storages
     *                        with books of different genres
     * @param bookReaderList  List of library readers
     */
    public Library(String libraryName, List<BookStorage> bookStorageList,
            List<BookReader> bookReaderList) {
        this.libraryName = libraryName;
        this.bookStorageList = bookStorageList;
        this.bookReaderList = bookReaderList;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public List<BookStorage> getBookStorageList() {
        return bookStorageList;
    }

    public List<BookReader> getBookReaderList() {
        return bookReaderList;
    }

    public boolean addBookStorage(BookStorage bookStorage) {
        return bookStorageList.add(bookStorage);
    }

    public boolean addBookStorages(Collection<BookStorage> bookStorages) {
        return bookStorageList.addAll(bookStorages);
    }

    public boolean addBookReader(BookReader bookReader) {
        return bookReaderList.add(bookReader);
    }

    public boolean addBookReaders(Collection<BookReader> bookReaders) {
        return bookReaderList.addAll(bookReaders);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(bookStorageList.size());
        for (BookStorage bookStorage : bookStorageList) {
            out.writeObject(bookStorage.getGenreStorageType());
            serializeBooks(out, bookStorage.getBookList());
        }

        out.writeInt(bookReaderList.size());
        for (BookReader bookReader : bookReaderList) {
            out.writeObject(bookReader.getFirstName());
            out.writeObject(bookReader.getLastName());
            out.writeInt(bookReader.getReaderId());

            serializeBooks(out, bookReader.getBorrowedBooks());
        }
    }

    private void serializeBooks(ObjectOutputStream out, List<Book> books)
            throws IOException {
        out.writeInt(books.size());
        for (Book book : books) {
            out.writeObject(book.getTitle());
            out.writeObject(book.getGenre());

            out.writeInt(book.getAuthorList().size());
            for (Author author : book.getAuthorList()) {
                out.writeObject(author.getFirstName());
                out.writeObject(author.getLastName());
            }
        }
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.bookStorageList = new ArrayList<>();
        this.bookReaderList = new ArrayList<>();

        int bookStorageListSize = in.readInt();
        for (int i = bookStorageListSize; i > 0; --i) {
            String bookStorageGenreType = (String) in.readObject();
            List<Book> books = readBooks(in);
            this.bookStorageList.add(new BookStorage(bookStorageGenreType, books));
        }

        int bookReadersListSize = in.readInt();
        for (int i = bookReadersListSize; i > 0; --i) {
            String firstName = (String) in.readObject();
            String lastName = (String) in.readObject();
            int readerId = in.readInt();

            List<Book> borrowedBooks = readBooks(in);
            this.bookReaderList.add(new BookReader(firstName, lastName, readerId, borrowedBooks));
        }
    }

    private List<Book> readBooks(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        List<Book> books = new ArrayList<>();
        int bookListSize = in.readInt();

        for (int i = bookListSize; i > 0; --i) {
            String title = (String) in.readObject();
            String genre = (String) in.readObject();

            List<Author> authors = new ArrayList<>();
            int authorListSize = in.readInt();
            for (int j = authorListSize; j > 0; --j) {
                String firstName = (String) in.readObject();
                String lastname = (String) in.readObject();

                authors.add(new Author(firstName, lastname));
            }
            books.add(new Book(title, genre, authors));
        }
        return books;
    }

    @Override
    public String toString() {
        StringBuilder storages = new StringBuilder();
        StringBuilder readers = new StringBuilder();
        bookStorageList
                .forEach(storage -> storages.append('\t').append(storage));
        bookReaderList.forEach(reader -> readers.append('\t').append(reader));
        return "Library '" + libraryName + "' has storages:\n" + storages
                + "has readers:\n" + readers;
    }
}
