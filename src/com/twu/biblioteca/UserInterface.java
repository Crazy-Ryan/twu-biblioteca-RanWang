package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private BookService bookService = new BookService();
    private boolean quitFlag = false;

    public void run() {
        greet();
        while (!quitFlag) {
            displayOptions();
            selectOptionOnMenu();
        }
    }

    protected void greet() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    protected void viewAvailableBookList() {
        List<Book> bookData = bookService.getAvailableBookData();
        System.out.print("Title\t| Author\t| Publication Year\n");
        for (Book book : bookData) {
            System.out.print(book.getTitle() + "\t| " + book.getAuthor() + "\t| " + book.getPublicationYear() + "\n");
        }
    }

    protected void displayOptions(){
        System.out.print("Please select an option\n" +
                "1. List of books\n" +
                "2. Check out a book\n" +
                "9. Quit\n");
    }
    protected void selectOptionOnMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                viewAvailableBookList();
                break;
            case 2:
                checkOutABook();
                break;
            case 9:
                quitFlag = true;
                break;
            default:
                System.out.print("Please select a valid option!\n");
        }
    }

    protected void checkOutABook(){
        String bookName = collectBookName();
        if(bookService.checkOutABook(bookName)){
            System.out.print("Thank you! Enjoy the book\n");
        }
    }

    protected String collectBookName(){
        System.out.print("Please enter the name of the book\n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
