package com.twu.biblioteca.entity;

public class Book extends Medium{
    private String author;
    private int publicationYear;
    private int userNumber;

    public Book(int id, String title, String author, int publicationYear, boolean available, int userNumber) {
        super(id, title, available);
        this.author = author;
        this.publicationYear = publicationYear;
        this.userNumber = userNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + this.getId() +
                ", title='" + this.getTitle() + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }

}
