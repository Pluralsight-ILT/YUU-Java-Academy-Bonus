package com.pluralsight.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("MOV001", "Inception", "Christopher Nolan", "Sci-Fi", 148);
    }

    @Test
    void testMovieCreation() {
        assertEquals("MOV001", movie.getId());
        assertEquals("Inception", movie.getTitle());
        assertEquals("Christopher Nolan", movie.getDirector());
        assertEquals("Christopher Nolan", movie.getCreator()); // Same as director
        assertEquals("Sci-Fi", movie.getGenre());
        assertEquals(148, movie.getDuration());
        assertEquals("Movie", movie.getType());
        assertEquals(3, movie.getBorrowDuration()); // 3 days for movies
        assertTrue(movie.isAvailable());
    }

    @Test
    void testMovieInheritance() {
        // Test that Movie is an Item
        assertTrue(movie instanceof Item);

        // Test inherited methods work
        assertEquals("MOV001", movie.getId());
        assertEquals("Christopher Nolan", movie.getCreator());
    }

    @Test
    void testMovieToString() {
        String expected = "MOV001 | Inception directed by Christopher Nolan [Sci-Fi] (148 min) - Available";
        assertEquals(expected, movie.toString());

        movie.setAvailable(false);
        expected = "MOV001 | Inception directed by Christopher Nolan [Sci-Fi] (148 min) - Borrowed";
        assertEquals(expected, movie.toString());
    }

    @Test
    void testMoviePolymorphism() {
        // Test polymorphism - treating Movie as Item
        Item itemRef = movie;
        assertEquals("Movie", itemRef.getType());
        assertEquals(3, itemRef.getBorrowDuration());
        assertEquals("Christopher Nolan", itemRef.getCreator());
    }

    @Test
    void testMovieWithAvailability() {
        Movie unavailableMovie = new Movie("MOV002", "The Matrix", "Wachowski Sisters", "Action", 136, false);
        assertFalse(unavailableMovie.isAvailable());
        assertEquals("Movie", unavailableMovie.getType());
        assertEquals(136, unavailableMovie.getDuration());
    }

    @Test
    void testMovieEquality() {
        Movie sameMovie = new Movie("MOV001", "Different Title", "Different Director", "Different Genre", 90);
        assertEquals(movie, sameMovie); // Should be equal because same ID
        assertEquals(movie.hashCode(), sameMovie.hashCode());

        Movie differentMovie = new Movie("MOV003", "Inception", "Christopher Nolan", "Sci-Fi", 148);
        assertNotEquals(movie, differentMovie);
    }
}