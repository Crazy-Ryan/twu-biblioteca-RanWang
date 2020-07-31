package com.twu.biblioteca;

import java.util.List;

public class BookService {
    private BookRepo bookRepo = new BookRepo();

    public List<Book> getBookData(){
        return bookRepo.getBookData();
    }

    public List<Book> getAvailableBookData(){
        return bookRepo.getAvailableBookData();
    }

    public boolean checkOutABook(String bookName){
        return bookRepo.checkOutABook(bookName);
    }
}
