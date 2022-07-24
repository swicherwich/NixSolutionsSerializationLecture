package com.nixsolutions.driver;

import com.nixsolutions.LibraryDriver;

import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static String libraryObjectFile = "lib.v2.obj";

    public static void main(String[] args) {
        Author author1 = new Author("A", "AA");
        Author author2 = new Author("B", "BB");

        Book book1 = new Book("Book-1", "Drama",
                Collections.singletonList(author1));
        Book book2 = new Book("Book-2", "Drama",
                Collections.singletonList(author2));

        BookStorage dramaStorage = new BookStorage("Drama",
                Arrays.asList(book1, book2));

        BookReader reader1 = new BookReader("Reader", "One", 1);
        BookReader reader2 = new BookReader("Reader", "Two", 2);

        reader1.borrowBook(book1);

        Library library = new Library("Main Library",
                Arrays.asList(dramaStorage),
                Arrays.asList(reader1, reader2));

        System.out.println("Before serialization:\n" + library);

        LibraryDriver.serializeObject(libraryObjectFile, library);

        Library deSerializeObject = (Library) LibraryDriver
                .deSerializeObject(libraryObjectFile);

        System.out.println("After deserialization:\n" + deSerializeObject);
    }
}