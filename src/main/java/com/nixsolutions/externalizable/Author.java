package com.nixsolutions.externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Author extends Person {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for {@code java.io.Serializable}
     */
    public Author() {
        super(null, null);
    }

    /**
     * Constructor for author with empty book list
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(firstName);
        out.writeObject(lastName);
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        this.firstName = (String) in.readObject();
        this.lastName = (String) in.readObject();
    }
}
