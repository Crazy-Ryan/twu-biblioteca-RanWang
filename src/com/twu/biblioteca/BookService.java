package com.twu.biblioteca;

import java.util.List;

public class BookService {
    private final BookRepo bookRepo = new BookRepo();

    public List<Book> getBooks() {
        return bookRepo.getBooks();
    }

    public List<Book> getAvailableBooks() {
        return bookRepo.getAvailableBooks();
    }

    public boolean checkOutABook(String bookName) {
        return bookRepo.checkOutABook(bookName);
    }

    public boolean returnABook(String bookName) {
        return bookRepo.returnABook(bookName);
    }
}
