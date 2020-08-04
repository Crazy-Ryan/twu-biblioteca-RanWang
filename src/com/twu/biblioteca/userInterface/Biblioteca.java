package com.twu.biblioteca.userInterface;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.service.AccountService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class Biblioteca {
    private final BookService bookService = new BookService();
    private final MovieService movieService = new MovieService();
    private final AccountService accountService = new AccountService();
    private Scanner scanner;

    private Map<Option, Integer> OPTION_NUMBER_MAP;
    private Map<Integer, Option> NUMBER_OPTION_MAP;
    private Map<Option, Supplier<Boolean>> OPTION_HANDLER_MAP;
    private boolean userLoggedIn = false;
    private int userNumber = -1;

    public void run() {
        init();
        greet();
        boolean exitAfterOptionHanding = false;
        while (!exitAfterOptionHanding) {
            displayOptions();
            exitAfterOptionHanding = selectOptionOnMenu();
        }
    }

    public void init() {
        final Map<Option, Integer> optionNumberMap = new HashMap<>();
        final Map<Integer, Option> numberOptionMap = new HashMap<>();
        final Map<Option, Supplier<Boolean>> optionHandlerMap = new HashMap<>();

        optionNumberMap.put(Option.LOG_IN, 0);
        optionNumberMap.put(Option.LIST_BOOK, 1);
        optionNumberMap.put(Option.CHECKOUT_BOOK, 2);
        optionNumberMap.put(Option.RETURN_BOOK, 3);
        optionNumberMap.put(Option.LIST_MOVIE, 4);
        optionNumberMap.put(Option.CHECKOUT_MOVIE, 5);
        optionNumberMap.put(Option.QUIT, 9);

        for (Option option : Option.values()) {
            numberOptionMap.put(optionNumberMap.get(option), option);
        }

        optionHandlerMap.put(Option.LOG_IN, this::userLogInHandler);
        optionHandlerMap.put(Option.LIST_BOOK, this::listBookHandler);
        optionHandlerMap.put(Option.CHECKOUT_BOOK, this::checkOutBookHandler);
        optionHandlerMap.put(Option.RETURN_BOOK, this::returnBookHandler);
        optionHandlerMap.put(Option.LIST_MOVIE, this::listMovieHandler);
        optionHandlerMap.put(Option.CHECKOUT_MOVIE, this::checkoutMovieHandler);
        optionHandlerMap.put(Option.QUIT, this::quitHandler);

        OPTION_NUMBER_MAP = Collections.unmodifiableMap(optionNumberMap);
        NUMBER_OPTION_MAP = Collections.unmodifiableMap(numberOptionMap);
        OPTION_HANDLER_MAP = Collections.unmodifiableMap(optionHandlerMap);
    }

    public void greet() {
        System.out.print("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    public void displayOptions() {
        System.out.print("Please select an option\n" +
                OPTION_NUMBER_MAP.get(Option.LOG_IN) + ". Log in\n" +
                OPTION_NUMBER_MAP.get(Option.LIST_BOOK) + ". List available books\n" +
                OPTION_NUMBER_MAP.get(Option.CHECKOUT_BOOK) + ". Check out a book\n" +
                OPTION_NUMBER_MAP.get(Option.RETURN_BOOK) + ". Return a book\n" +
                OPTION_NUMBER_MAP.get(Option.LIST_MOVIE) + ". List available movies\n" +
                OPTION_NUMBER_MAP.get(Option.CHECKOUT_MOVIE) + ". Check out a movie\n" +
                OPTION_NUMBER_MAP.get(Option.QUIT) + ". Quit\n");
    }

    public boolean selectOptionOnMenu() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (RuntimeException e) {
            return invalidOptionHandler();
        }
        Supplier<Boolean> optionHandler = OPTION_HANDLER_MAP.get(NUMBER_OPTION_MAP.get(choice));
        if (null == optionHandler) {
            optionHandler = this::invalidOptionHandler;
        }
        return optionHandler.get();
    }

    public boolean listBookHandler() {
        List<Book> bookData = bookService.getAvailableBooks();
        System.out.print("Title\t| Author\t| Publication Year\n");
        for (Book book : bookData) {
            System.out.print(book.getTitle() + "\t| " + book.getAuthor() + "\t| " + book.getPublicationYear() + "\n");
        }
        return false;

    }

    public boolean checkOutBookHandler() {
        checkLoginStatus();
        System.out.print("Please enter the title\n");
        String bookName = collectALine();
        if (bookService.checkOutABook(bookName, userNumber)) {
            System.out.print("Thank you! Enjoy the book\n");
        } else {
            System.out.print("Sorry, that book is not available\n");
        }
        return false;
    }

    public boolean returnBookHandler() {
        checkLoginStatus();
        System.out.print("Please enter the title\n");
        String bookName = collectALine();
        if (bookService.returnABook(bookName, userNumber)) {
            System.out.print("Thank you for returning the book\n");
        } else {
            System.out.print("That is not a valid book to return\n");
        }
        return false;
    }

    public boolean listMovieHandler() {
        List<Movie> movies = movieService.getAvailableMovies();
        System.out.print("Title\t| Year\t| Director\t| Rating\n");
        for (Movie movie : movies) {
            System.out.print(movie.getTitle() + "\t| " + movie.getReleaseYear() + "\t| " + movie.getDirector() + "\t| " + movie.getRating() + "\n");
        }
        return false;
    }

    public boolean checkoutMovieHandler() {
        System.out.print("Please enter the title\n");
        String movieName = collectALine();
        if (movieService.checkOutAMovie(movieName)) {
            System.out.print("Thank you! Enjoy the movie\n");
        } else {
            System.out.print("Sorry, that movie is not available\n");
        }
        return false;
    }

    public boolean quitHandler() {
        return true;
    }

    public boolean invalidOptionHandler() {
        System.out.print("Please select a valid option!\n");
        return false;
    }

    public String collectALine() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

    public void checkLoginStatus() {
        if (!userLoggedIn) {
            System.out.print("--Please login for further operations--\n");
            userLogInHandler();
        }
    }

    public boolean userLogInHandler() {
        int userNumberCollected;
        do {
            System.out.print("Please enter your user number (xxx-xxxx):\n");
            String userNumberEntered = collectALine();
            userNumberCollected = Integer.parseInt(userNumberEntered.replaceFirst("-", ""));
            System.out.print("Please enter your password:\n");
            String userPasswordEntered = collectALine();
            userLoggedIn = accountService.userLogin(userNumberCollected, userPasswordEntered);
            if (!userLoggedIn) {
                System.out.print("Login failed. Please try again\n");
            }
        } while (!userLoggedIn);
        System.out.print("Login successfully. Welcome!\n");
        userNumber = userNumberCollected;
        return false;
    }
}
