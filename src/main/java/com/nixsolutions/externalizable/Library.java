package com.nixsolutions.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Library implements Externalizable {

    private static final long serialVersionUID = 1L;

    private String libraryName;

    private transient List<BookStorage> bookStorageList;
    private transient List<BookReader> bookReaderList;

    /**
     * Constructor for {@code java.io.Serializable}
     */
    public Library() {
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(libraryName);
        out.writeInt(bookStorageList.size());
        for(BookStorage storage: bookStorageList) {
            out.writeObject(storage);
        }
        out.writeInt(bookReaderList.size());
        for(BookReader reader: bookReaderList) {
            out.writeObject(reader);
        }
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        this.libraryName = (String) in.readObject();
        this.bookStorageList = new ArrayList<>();
        this.bookReaderList = new ArrayList<>();

        int bookStoragesListSize = in.readInt();
        for(int i = bookStoragesListSize; i > 0; --i) {
            bookStorageList.add((BookStorage) in.readObject());
        }

        int bookReadersListSize = in.readInt();
        for(int i = bookReadersListSize; i > 0; --i) {
            bookReaderList.add((BookReader) in.readObject());
        }
    }
}
