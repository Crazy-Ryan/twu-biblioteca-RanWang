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

    public boolean checkOutABook(String bookName, int userNumber) {
        return bookRepo.checkoutMedium(bookName, userNumber);
    }

    public boolean returnABook(String bookName, int userNumber) {
        return bookRepo.returnMedium(bookName, userNumber);
    }
}
