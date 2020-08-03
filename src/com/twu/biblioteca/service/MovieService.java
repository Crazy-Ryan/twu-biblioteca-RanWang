package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.repo.MovieRepo;

import java.util.List;

public class MovieService {
    private final MovieRepo movieRepo = new MovieRepo();

    public List<Movie> getMovies() {
        return movieRepo.getMedia();
    }

    public List<Movie> getAvailableMovies() {
        return movieRepo.getAvailableMedia();
    }

    public boolean checkOutAMovie(String bookName) {
        return movieRepo.checkoutMedium(bookName);
    }

    public boolean returnAMovie(String bookName) {
        return movieRepo.returnMedium(bookName);
    }
}
