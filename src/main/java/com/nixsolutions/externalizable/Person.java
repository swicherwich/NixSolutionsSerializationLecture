package com.nixsolutions.externalizable;

import java.io.Externalizable;

/**
 * Base class for creating entities with first and last names
 */
public abstract class Person implements Externalizable {

    protected String firstName;
    protected String lastName;

    protected Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
