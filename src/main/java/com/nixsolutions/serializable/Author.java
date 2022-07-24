package com.nixsolutions.serializable;

public class Author extends Person {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for author with empty book list
     *
     */
    public Author(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
