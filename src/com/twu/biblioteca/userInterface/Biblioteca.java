package com.twu.biblioteca.userInterface;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
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
    private Map<Option, Integer> OPTION_NUMBER_MAP;
    private Map<Integer, Option> NUMBER_OPTION_MAP;
    private Map<Option, Supplier<Boolean>> OPTION_HANDLER_MAP;

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

        optionNumberMap.put(Option.LIST_BOOK, 1);
        optionNumberMap.put(Option.CHECKOUT_BOOK, 2);
        optionNumberMap.put(Option.RETURN_BOOK, 3);
        optionNumberMap.put(Option.LIST_MOVIE, 4);
        optionNumberMap.put(Option.CHECKOUT_MOVIE, 5);
        optionNumberMap.put(Option.QUIT, 9);

        for (Option option : Option.values()) {
            numberOptionMap.put(optionNumberMap.get(option), option);
        }

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
                OPTION_NUMBER_MAP.get(Option.LIST_BOOK) + ". List available books\n" +
                OPTION_NUMBER_MAP.get(Option.CHECKOUT_BOOK) + ". Check out a book\n" +
                OPTION_NUMBER_MAP.get(Option.RETURN_BOOK) + ". Return a book\n" +
                OPTION_NUMBER_MAP.get(Option.LIST_MOVIE) + ". List available movies\n" +
                OPTION_NUMBER_MAP.get(Option.CHECKOUT_MOVIE) + ". Check out a movie\n" +
                OPTION_NUMBER_MAP.get(Option.QUIT) + ". Quit\n");
    }

    public boolean selectOptionOnMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            choice = scanner.nextInt();
        }catch (RuntimeException e){
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
        String bookName = collectTitle();
        if (bookService.checkOutABook(bookName)) {
            System.out.print("Thank you! Enjoy the book\n");
        } else {
            System.out.print("Sorry, that book is not available\n");
        }
        return false;
    }

    public boolean returnBookHandler() {
        String bookName = collectTitle();
        if (bookService.returnABook(bookName)) {
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
            System.out.print(movie.getTitle() + "\t| " + movie.getReleaseYear() + "\t| " + movie.getDirector() +  "\t| " + movie.getRating() +"\n");
        }
        return false;
    }

    public boolean checkoutMovieHandler() {
        String movieName = collectTitle();
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

    public String collectTitle() {
        System.out.print("Please enter the title\n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
