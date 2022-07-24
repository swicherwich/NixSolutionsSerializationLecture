package com.nixsolutions.serializable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String title;

    private final String genre;

    private final List<Author> authorList;

    public Book(String title, String genre, List<Author> authorList) {
        this.title = title;
        this.genre = genre;
        this.authorList = authorList;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public boolean addAuthor(Author author) {
        return authorList.add(author);
    }

    public boolean addAuthors(Collection<Author> authors) {
        return authorList.addAll(authors);
    }

    @Override
    public String toString() {
        StringJoiner authors = new StringJoiner(",");
        authorList.forEach(author -> authors.add(author.toString()));
        return title + " of " + genre.toLowerCase() + " genre" + " written by "
                + authors;
    }
}
