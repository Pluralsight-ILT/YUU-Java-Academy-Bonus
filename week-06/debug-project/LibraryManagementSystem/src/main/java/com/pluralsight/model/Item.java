package com.pluralsight.model;

public class Item {
    protected String id;
    protected String title;
    protected String creator; // Could be an author, director, publisher, etc.
    protected String genre;
    protected boolean isAvailable;

    public Item(String id, String title, String creator, String genre) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.genre = genre;
        this.isAvailable = true;
    }

    public Item(String id, String title, String creator, String genre, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.genre = genre;
        this.isAvailable = isAvailable;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getCreator() { return creator; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return isAvailable; }

    // Setters
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // Default implementations - can be overridden by subclasses
    public String getType() {
        return "Item";
    }

    public int getBorrowDuration() {
        return 7; // Default 1 week
    }

    @Override
    public String toString() {
        return String.format("%s | %s by %s [%s] - %s",
                id, title, creator, genre, isAvailable ? "Available" : "Borrowed");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}