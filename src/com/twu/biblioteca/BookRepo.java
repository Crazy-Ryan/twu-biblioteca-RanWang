package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookRepo {
    private List<Book> bookData;

    public BookRepo() {
        bookData = new ArrayList(Arrays.asList(
                new Book(1, "Pride and Prejudice", "Jane Austen", 1813),
                new Book(2, "Jane Eyre", "Charlotte Brontë", 1847),
                new Book(3, "Gone With the Wind", "Margaret Mitchell", 1936)
        ));
    }

    public List<Book> getBookData() {
        return bookData;
    }
}
