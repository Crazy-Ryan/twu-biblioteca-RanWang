package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookRepo {
    private List<Book> bookData;

    public BookRepo() {
        bookData = new ArrayList(Arrays.asList(
                new Book(1, "Pride and Prejudice"),
                new Book(2, "Jane Eyre"),
                new Book(3, "Gone With the Wind")
        ));
    }

    public List<Book> getBookData() {
        return bookData;
    }
}
