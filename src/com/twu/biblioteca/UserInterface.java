package com.twu.biblioteca;

public class UserInterface {
    private BookService bookService = new BookService();

    public void run() {
        greet();
    }

    public void greet() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }
}
