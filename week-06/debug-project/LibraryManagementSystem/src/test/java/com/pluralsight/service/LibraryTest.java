package com.pluralsight.service;

import com.pluralsight.model.Book;
import com.pluralsight.model.Movie;
import com.pluralsight.model.Magazine;
import com.pluralsight.model.Member;
import com.pluralsight.model.Item;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book book1, book2, book3;
    private Movie movie1;
    private Magazine magazine1;
    private Member member1, member2;

    @BeforeEach
    void setUp() {
        library = new Library();

        // Create test items
        book1 = new Book("1234567890", "Java Programming", "John Author", "Programming");
        book2 = new Book("0987654321", "Data Structures", "Jane Writer", "Computer Science");
        book3 = new Book("1111111111", "Web Development", "Bob Coder", "Programming");
        movie1 = new Movie("MOV001", "Inception", "Christopher Nolan", "Sci-Fi", 148);
        magazine1 = new Magazine("MAG001", "Tech Weekly", "Tech Publications", "Technology", "Issue 42", "2025-05-01");

        // Create test members
        member1 = new Member("M1001", "Alice Johnson", "alice@test.com");
        member2 = new Member("M1002", "Bob Smith", "bob@test.com");

        // Add to library
        library.addItem(book1);
        library.addItem(book2);
        library.addItem(book3);
        library.addItem(movie1);
        library.addItem(magazine1);
        library.addMember(member1);
        library.addMember(member2);
    }

    @Test
    void testAddItem() {
        Book newBook = new Book("2222222222", "New Book", "New Author", "Fiction");
        assertTrue(library.addItem(newBook));
        assertFalse(library.addItem(newBook)); // Can't add same item twice

        Movie newMovie = new Movie("MOV002", "The Matrix", "Wachowski Sisters", "Action", 136);
        assertTrue(library.addItem(newMovie));
    }

    @Test
    void testAddMember() {
        Member newMember = new Member("M1003", "Charlie Brown", "charlie@test.com");
        assertTrue(library.addMember(newMember));
        assertFalse(library.addMember(newMember)); // Can't add same member twice
    }

    @Test
    void testSearchItems() {
        List<Item> results = library.searchItems("Java");
        assertEquals(1, results.size());
        assertEquals("Java Programming", results.get(0).getTitle());

        results = library.searchItems("Programming");
        assertEquals(2, results.size()); // Should find both programming books

        results = library.searchItems("Inception");
        assertEquals(1, results.size());
        assertEquals("Movie", results.get(0).getType());

        results = library.searchItems("NonExistent");
        assertEquals(0, results.size());
    }

    @Test
    void testSearchByGenre() {
        List<Item> results = library.searchByGenre("Programming");
        assertEquals(2, results.size());

        results = library.searchByGenre("Computer Science");
        assertEquals(1, results.size());
        assertEquals("Data Structures", results.get(0).getTitle());

        results = library.searchByGenre("Sci-Fi");
        assertEquals(1, results.size());
        assertEquals("Movie", results.get(0).getType());
    }

    @Test
    void testSearchByCreator() {
        List<Item> results = library.searchByCreator("John Author");
        assertEquals(1, results.size());
        assertEquals("Java Programming", results.get(0).getTitle());

        results = library.searchByCreator("Christopher Nolan");
        assertEquals(1, results.size());
        assertEquals("Inception", results.get(0).getTitle());

        results = library.searchByCreator("john"); // Case insensitive
        assertEquals(1, results.size());
    }

    @Test
    void testBorrowItem() {
        assertTrue(library.borrowItem("M1001", "1234567890"));
        assertFalse(book1.isAvailable());
        assertTrue(member1.hasBorrowedBook("1234567890"));

        // Can't borrow same item again
        assertFalse(library.borrowItem("M1001", "1234567890"));

        // Can't borrow already borrowed item
        assertFalse(library.borrowItem("M1002", "1234567890"));

        // Can borrow different types
        assertTrue(library.borrowItem("M1001", "MOV001"));
        assertTrue(library.borrowItem("M1002", "MAG001"));
    }

    @Test
    void testReturnItem() {
        // First borrow an item
        library.borrowItem("M1001", "1234567890");

        // Then return it
        assertTrue(library.returnItem("M1001", "1234567890"));
        assertTrue(book1.isAvailable());
        assertFalse(member1.hasBorrowedBook("1234567890"));

        // Can't return an item not borrowed
        assertFalse(library.returnItem("M1001", "1234567890"));
    }

    @Test
    void testBorrowNonExistentItem() {
        assertFalse(library.borrowItem("M1001", "9999999999"));
    }

    @Test
    void testBorrowWithNonExistentMember() {
        assertFalse(library.borrowItem("M9999", "1234567890"));
    }

    @Test
    void testReturnWithNonExistentMember() {
        assertFalse(library.returnItem("M9999", "1234567890"));
    }

    @Test
    void testGetAvailableItems() {
        List<Item> available = library.getAvailableItems();
        assertEquals(5, available.size());

        // Borrow an item
        library.borrowItem("M1001", "1234567890");
        available = library.getAvailableItems();
        assertEquals(4, available.size());
    }

    @Test
    void testMemberBorrowMultipleItems() {
        assertTrue(library.borrowItem("M1001", "1234567890"));
        assertTrue(library.borrowItem("M1001", "MOV001"));
        assertTrue(library.borrowItem("M1001", "MAG001"));

        assertEquals(3, member1.getBorrowedCount());
        assertTrue(member1.hasBorrowedBook("1234567890"));
        assertTrue(member1.hasBorrowedBook("MOV001"));
        assertTrue(member1.hasBorrowedBook("MAG001"));
    }

    @Test
    void testValidateISBN() {
        assertTrue(Library.isValidISBN("1234567890")); // 10 digits
        assertTrue(Library.isValidISBN("1234567890123")); // 13 digits
        assertFalse(Library.isValidISBN("123"));
        assertFalse(Library.isValidISBN("abc1234567"));
        assertFalse(Library.isValidISBN(null));
    }

    @Test
    void testValidateEmail() {
        assertTrue(Library.isValidEmail("test@example.com"));
        assertTrue(Library.isValidEmail("user.name@domain.co.uk"));
        assertFalse(Library.isValidEmail("invalid"));
        assertFalse(Library.isValidEmail("test@"));
        assertFalse(Library.isValidEmail("@domain.com"));
        assertFalse(Library.isValidEmail(null));
    }

    @Test
    void testGenerateMemberId() {
        String id1 = Library.generateMemberId();
        String id2 = Library.generateMemberId();
        assertNotEquals(id1, id2);
        assertTrue(id1.startsWith("M"));
        assertTrue(id2.startsWith("M"));
    }

    @Test
    void testItemEquality() {
        Book book1Copy = new Book("1234567890", "Different Title", "Different Author", "Different Genre");
        assertEquals(book1, book1Copy); // Should be equal because same ID
        assertEquals(book1.hashCode(), book1Copy.hashCode());
    }

    @Test
    void testMemberEquality() {
        Member member1Copy = new Member("M1001", "Different Name", "different@email.com");
        assertEquals(member1, member1Copy); // Should be equal because same ID
        assertEquals(member1.hashCode(), member1Copy.hashCode());
    }

    @Test
    void testPolymorphism() {
        // Test that we can treat all items polymorphically
        List<Item> allItems = library.getAllItems();
        boolean foundBook = false, foundMovie = false, foundMagazine = false;

        for (Item item : allItems) {
            if (item.getType().equals("Book")) foundBook = true;
            if (item.getType().equals("Movie")) foundMovie = true;
            if (item.getType().equals("Magazine")) foundMagazine = true;
        }

        assertTrue(foundBook);
        assertTrue(foundMovie);
        assertTrue(foundMagazine);
    }
}