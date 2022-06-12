package org.example.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 * created by dmitry.fateev
 * 07.06.2022
 */

public class Book {

    private int book_id;

//    private int user_id;

    @NotEmpty(message = "Title should not be empty")
    @Pattern(regexp = "[A-Z]\\w+")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author's  should be start with a uppercase letter")
    private String author;

    @Min(value = 0, message = "Year should be above zero")
    private int year;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }
}
