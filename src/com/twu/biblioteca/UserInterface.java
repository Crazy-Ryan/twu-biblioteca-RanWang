package com.twu.biblioteca;

import java.util.List;

public class UserInterface {
    private BookService bookService = new BookService();

    public void run() {
        greet();
        viewBookList();
    }

    protected void greet() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    protected void viewBookList() {
        List<Book> bookData = bookService.getBookData();
        for(Book book:bookData){
            System.out.print(book.getTitle() + "\n");
        }
    }
}
