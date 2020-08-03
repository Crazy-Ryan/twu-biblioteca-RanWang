package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repo.BookRepo;

import java.util.List;

public class BookService {
    private final BookRepo bookRepo = new BookRepo();

    public List<Book> getBooks() {
        return bookRepo.getMedia();
    }


    public List<Book> getAvailableBooks() {
        return bookRepo.getAvailableMedia();
    }

    public boolean checkOutABook(String bookName) {
        return bookRepo.checkoutMedium(bookName);
    }

    public boolean returnABook(String bookName) {
        return bookRepo.returnMedium(bookName);
    }
}
