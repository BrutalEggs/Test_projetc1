package org.example.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 * created by dmitry.fateev
 * 07.06.2022
 */

public class Person {


    private int user_id;

    @NotEmpty
    @Pattern(regexp = "[A-Z]\\w [A-Z]\\w [A-Z]\\w", message = "All should be start with a uppercase letter")
    private String name;

    @Min(value = 1900, message = "Year of birth should be over 1900")
    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Person() {
    }

    public Person(int user_id, String name, int year) {
        this.name = name;
        this.year = year;
    }
}
