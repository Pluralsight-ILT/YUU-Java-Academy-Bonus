package com.pluralsight.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("1234567890", "Test Book", "Test Author", "Fiction");
    }

    @Test
    void testBookCreation() {
        assertEquals("1234567890", book.getIsbn());
        assertEquals("1234567890", book.getId()); // Same as ISBN
        assertEquals("Test Book", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals("Test Author", book.getCreator()); // Same as author
        assertEquals("Fiction", book.getGenre());
        assertTrue(book.isAvailable());
        assertEquals("Book", book.getType());
        assertEquals(14, book.getBorrowDuration());
    }

    @Test
    void testBookInheritance() {
        // Test that Book is an Item
        assertTrue(book instanceof Item);

        // Test inherited methods work
        assertEquals("1234567890", book.getId());
        assertEquals("Test Author", book.getCreator());
    }

    @Test
    void testBookAvailability() {
        assertTrue(book.isAvailable());
        book.setAvailable(false);
        assertFalse(book.isAvailable());
        book.setAvailable(true);
        assertTrue(book.isAvailable());
    }

    @Test
    void testBookEquality() {
        Book sameBook = new Book("1234567890", "Different Title", "Different Author", "Different Genre");
        assertEquals(book, sameBook); // Should be equal because same ISBN
        assertEquals(book.hashCode(), sameBook.hashCode());

        Book differentBook = new Book("0987654321", "Test Book", "Test Author", "Fiction");
        assertNotEquals(book, differentBook);
    }

    @Test
    void testBookToString() {
        String expected = "1234567890 | Test Book by Test Author [Fiction] - Available";
        assertEquals(expected, book.toString());

        book.setAvailable(false);
        expected = "1234567890 | Test Book by Test Author [Fiction] - Borrowed";
        assertEquals(expected, book.toString());
    }

    @Test
    void testBookWithAvailability() {
        Book unavailableBook = new Book("1111111111", "Unavailable Book", "Some Author", "Mystery", false);
        assertFalse(unavailableBook.isAvailable());
        assertEquals("Book", unavailableBook.getType());
    }
}