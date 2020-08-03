package com.twu.biblioteca.repo;

import com.twu.biblioteca.entity.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRepo implements MediumRepo<Movie> {
    private final List<Movie> movies;

    public MovieRepo() {
        movies = new ArrayList(Arrays.asList(
                new Movie(1, "The Godfather", 1972, "Francis Ford Coppola", 9.2, true),
                new Movie(2, "The Dark Knight", 2008, "Christopher Nolan", 9.0, false),
                new Movie(3, "12 Angry Men", 1957, "Sidney Lumet", 8.9, true)
        ));
    }

    @Override
    public List<Movie> getMedia() {
        return movies;
    }

    @Override
    public List<Movie> getAvailableMedia() {
        return movies.stream().filter(Movie::isAvailable).collect(Collectors.toList());
    }

    @Override
    public boolean checkoutMedium(String bookName) {
        for (Movie movie : movies) {
            if ((movie.isAvailable()) && (movie.getTitle().equals(bookName))) {
                movie.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean returnMedium(String bookName) {
        for (Movie movie : movies) {
            if ((!movie.isAvailable()) && (movie.getTitle().equals(bookName))) {
                movie.setAvailable(true);
                return true;
            }
        }
        return false;
    }
}