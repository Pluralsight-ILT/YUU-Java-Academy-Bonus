package com.pluralsight.BookRecommendationAPI;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private double rating;
    private int yearPublished;
    private String description;
    private boolean isAvailable;

    public Book() {}

    public Book(int id, String title, String author, String genre, double rating,
                int yearPublished, String description, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.yearPublished = yearPublished;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", yearPublished=" + yearPublished +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}