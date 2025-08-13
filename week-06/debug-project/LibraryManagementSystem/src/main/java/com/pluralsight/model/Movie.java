package com.pluralsight.model;

public class Movie extends Item {
    private int duration; // in minutes

    public Movie(String movieId, String title, String director, String genre, int duration) {
        super(movieId, title, director, genre);
        this.duration = duration;
    }

    public Movie(String movieId, String title, String director, String genre, int duration, boolean isAvailable) {
        super(movieId, title, director, genre, isAvailable);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public String getDirector() {
        return getCreator();
    }

    @Override
    public String getType() {
        return "Movie";
    }

    @Override
    public int getBorrowDuration() {
        return duration; // 3 days for movies
    }

    @Override
    public String toString() {
        return String.format("%s | %s directed by %s [%s] (%d min) - %s",
                getId(), getTitle(), getDirector(), getGenre(), duration,
                isAvailable() ? "Available" : "Borrowed");
    }
}