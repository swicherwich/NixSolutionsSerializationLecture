package com.nixsolutions.driver;

import java.io.Serializable;

/**
 * Base class for creating entities with first and last names
 */
public abstract class Person implements Serializable {

    protected String firstName;
    protected String lastName;

    protected Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
