package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepo {
    private List<Book> bookData;

    public BookRepo() {
        bookData = new ArrayList(Arrays.asList(
                new Book(1, "Pride and Prejudice", "Jane Austen", 1813, true),
                new Book(2, "Jane Eyre", "Charlotte Brontë", 1847, true),
                new Book(3, "Gone With the Wind", "Margaret Mitchell", 1936, true)
        ));
    }

    public List<Book> getBookData() {
        return bookData;
    }

    public List<Book> getAvailableBookData() {
        return bookData.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public boolean checkOutABook(String bookName) {
        for (Book book : bookData) {
            if ((book.isAvailable()) && (book.getTitle().equals(bookName))) {
                book.setAvailable(false);
                return true;
            }
        }
        return false;
    }
    public boolean returnABook(String bookName) {
        for (Book book : bookData) {
            if ((!book.isAvailable()) && (book.getTitle().equals(bookName))) {
                book.setAvailable(true);
                return true;
            }
        }
        return false;
    }
}