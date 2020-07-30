package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private BookService bookService = new BookService();

    public void run() {
        greet();
        selectOptionOnMenu();
    }

    protected void greet() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    protected void viewBookList() {
        List<Book> bookData = bookService.getBookData();
        System.out.print("Title\t| Author\t| Publication Year\n");
        for (Book book : bookData) {
            System.out.print(book.getTitle() + "\t| " + book.getAuthor() + "\t| " + book.getPublicationYear() + "\n");
        }
    }

    protected void selectOptionOnMenu() {
        System.out.print("Please select an option\n" +
                "1. List of books\n");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                viewBookList();
                break;
            default:
                System.out.println("unavailable");
        }
    }
}
