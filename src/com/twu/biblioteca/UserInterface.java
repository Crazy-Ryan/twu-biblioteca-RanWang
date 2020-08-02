package com.twu.biblioteca;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class UserInterface {
    private final BookService bookService = new BookService();
    private Map<Option, Integer> OPTION_NUMBER_MAP;
    private Map<Integer, Option> NUMBER_OPTION_MAP;
    private Map<Option, Supplier<Boolean>> OPTION_HANDLER_MAP;

    public void run() {
        init();
        greet();
        selectOptionOnMenu();
    }

    public void init() {
        final Map<Option, Integer> optionNumberMap = new HashMap<>();
        final Map<Integer, Option> numberOptionMap = new HashMap<>();
        final Map<Option, Supplier<Boolean>> optionHandlerMap = new HashMap<>();

        optionNumberMap.put(Option.LIST_BOOK, 1);
        optionNumberMap.put(Option.CHECKOUT_BOOK, 2);
        optionNumberMap.put(Option.RETURN_BOOK, 3);
        optionNumberMap.put(Option.QUIT, 9);

        for (Option option : Option.values()) {
            numberOptionMap.put(optionNumberMap.get(option), option);
        }

        optionHandlerMap.put(Option.LIST_BOOK, this::listBookHandler);
        optionHandlerMap.put(Option.CHECKOUT_BOOK, this::checkOutBookHandler);
        optionHandlerMap.put(Option.RETURN_BOOK, this::returnBookHandler);
        optionHandlerMap.put(Option.QUIT, this::quitHandler);

        OPTION_NUMBER_MAP = Collections.unmodifiableMap(optionNumberMap);
        NUMBER_OPTION_MAP = Collections.unmodifiableMap(numberOptionMap);
        OPTION_HANDLER_MAP = Collections.unmodifiableMap(optionHandlerMap);
    }

    protected void greet() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    protected void displayOptions() {
        System.out.print("Please select an option\n" +
                OPTION_NUMBER_MAP.get(Option.LIST_BOOK) + ". List of books\n" +
                OPTION_NUMBER_MAP.get(Option.CHECKOUT_BOOK) + ". Check out a book\n" +
                OPTION_NUMBER_MAP.get(Option.RETURN_BOOK) + ". Return a book\n" +
                OPTION_NUMBER_MAP.get(Option.QUIT) + ". Quit\n");
    }

    protected void selectOptionOnMenu() {
        boolean exitAfterOptionHanding = false;
        while (!exitAfterOptionHanding) {
            displayOptions();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            Supplier<Boolean> optionHandler = OPTION_HANDLER_MAP.get(NUMBER_OPTION_MAP.get(choice));
            if (null == optionHandler) {
                optionHandler = this::invalidOptionHandler;
            }
            exitAfterOptionHanding = optionHandler.get();
        }
    }

    protected boolean listBookHandler() {
        List<Book> bookData = bookService.getAvailableBookData();
        System.out.print("Title\t| Author\t| Publication Year\n");
        for (Book book : bookData) {
            System.out.print(book.getTitle() + "\t| " + book.getAuthor() + "\t| " + book.getPublicationYear() + "\n");
        }
        return false;
    }

    protected boolean checkOutBookHandler() {
        String bookName = collectBookName();
        if (bookService.checkOutABook(bookName)) {
            System.out.print("Thank you! Enjoy the book\n");
        } else {
            System.out.print("Sorry, that book is not available\n");
        }
        return false;
    }

    protected boolean returnBookHandler() {
        String bookName = collectBookName();
        if (bookService.returnABook(bookName)) {
            System.out.print("Thank you for returning the book\n");
        } else {
            System.out.print("That is not a valid book to return\n");
        }
        return false;
    }

    protected boolean quitHandler() {
        return true;
    }

    protected boolean invalidOptionHandler() {
        System.out.print("Please select a valid option!\n");
        return false;
    }

    protected String collectBookName() {
        System.out.print("Please enter the name of the book\n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
