package com.twu.biblioteca.repo;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepo implements MediumRepo<Book> {
    private final List<Book> books;

    public BookRepo() {
        books = new ArrayList(Arrays.asList(
                new Book(1, "Pride and Prejudice", "Jane Austen", 1813, true),
                new Book(2, "Jane Eyre", "Charlotte Brontë", 1847, false),
                new Book(3, "Gone With the Wind", "Margaret Mitchell", 1936, true)
        ));
    }

    @Override
    public List<Book> getMedia() {
        return books;
    }

    @Override
    public List<Book> getAvailableMedia() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    @Override
    public boolean checkoutMedium(String bookName) {
        for (Book book : books) {
            if ((book.isAvailable()) && (book.getTitle().equals(bookName))) {
                book.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean returnMedium(String bookName) {
        for (Book book : books) {
            if ((!book.isAvailable()) && (book.getTitle().equals(bookName))) {
                book.setAvailable(true);
                return true;
            }
        }
        return false;
    }
}