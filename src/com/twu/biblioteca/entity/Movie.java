package com.twu.biblioteca.entity;

public class Movie extends Medium {
    private int releaseYear;
    private String director;
    private Double rating;

    public Movie(int id, String title, int releaseYear, String director, Double rating, boolean available) {
        super(id, title, available);
        this.releaseYear = releaseYear;
        this.director = director;
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
