package com.nixsolutions.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public class Book implements Externalizable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String genre;

    private List<Author> authorList;

    /**
     * Constructor for {@code java.io.Serializable}
     */
    public Book() {
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeObject(genre);
        out.writeInt(authorList.size());
        for(Author author: authorList) {
            out.writeObject(author);
        }
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        this.title = (String) in.readObject();
        this.genre = (String) in.readObject();
        this.authorList = new ArrayList<>();

        int authorListSize = in.readInt();
        for(int i = authorListSize; i > 0; --i) {
            authorList.add((Author) in.readObject());
        }
    }
}
