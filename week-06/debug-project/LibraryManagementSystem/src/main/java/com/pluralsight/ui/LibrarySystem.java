package com.pluralsight.ui;

import com.pluralsight.model.Item;
import com.pluralsight.model.Book;
import com.pluralsight.model.Movie;
import com.pluralsight.model.Magazine;
import com.pluralsight.model.Member;
import com.pluralsight.service.Library;
import com.pluralsight.service.Logger;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    private Library library;
    private Scanner scanner;
    private Logger logger;

    public LibrarySystem() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
        this.logger = Logger.getInstance();
    }

    public void start() {
        logger.info("Library Management System started");
        System.out.println("=== Welcome to Library Management System ===");

        // Try to load existing data
        try {
            library.loadFromCSV();
            System.out.println("Library data loaded successfully.");
            System.out.println("Items loaded: " + library.getAllItems().size() +
                    ", Members: " + library.getAllItems().stream().mapToInt(b -> library.getMember("M1001") != null ? 1 : 0).sum());
        } catch (IOException e) {
            logger.error("Failed to load library data", e);
            System.out.println("Warning: Could not load library data. Starting with empty library.");
            System.out.println("Make sure the data/ directory exists with items.csv, members.csv, and borrowed.csv files.");
        }

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getChoice();
            logger.debug("User selected main menu option: " + choice);

            switch (choice) {
                case 1:
                    browseByItemType();
                    break;
                case 2:
                    searchAllItems();
                    break;
                case 3:
                    borrowItem();
                    break;
                case 4:
                    returnItem();
                    break;
                case 5:
                    registerMember();
                    break;
                case 6:
                    viewMemberInfo();
                    break;
                case 7:
                    viewAllItems();
                    break;
                case 8:
                    saveAndExit();
                    running = false;
                    break;
                default:
                    logger.warn("Invalid menu choice entered: " + choice);
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== Library Main Menu ===");
        System.out.println("1. Browse by Item Type (Books/Movies/Magazines)");
        System.out.println("2. Search All Items");
        System.out.println("3. Borrow Item");
        System.out.println("4. Return Item");
        System.out.println("5. Register New Member");
        System.out.println("6. View Member Information");
        System.out.println("7. View All Items");
        System.out.println("8. Save and Exit");
        System.out.print("Enter your choice: ");
    }

    private void browseByItemType() {
        System.out.println("\n=== Browse by Item Type ===");
        System.out.println("1. Books");
        System.out.println("2. Movies");
        System.out.println("3. Magazines");
        System.out.println("4. Back to Main Menu");
        System.out.print("Choose item type: ");

        int choice = getChoice();
        logger.debug("User selected item type: " + choice);

        switch (choice) {
            case 1:
                bookMenu();
                break;
            case 2:
                movieMenu();
                break;
            case 3:
                magazineMenu();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // FULLY IMPLEMENTED BOOK MENU (Reference for students)
    private void bookMenu() {
        boolean inBookMenu = true;
        while (inBookMenu) {
            System.out.println("\n=== Book Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. View Available Books");
            System.out.println("3. Search Books");
            System.out.println("4. Search by Author");
            System.out.println("5. Search by Genre");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getChoice();
            logger.debug("User selected book menu option: " + choice);

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    viewAvailableBooks();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    searchBooksByAuthor();
                    break;
                case 5:
                    searchBooksByGenre();
                    break;
                case 6:
                    borrowSpecificBook();
                    break;
                case 7:
                    returnSpecificBook();
                    break;
                case 8:
                    inBookMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // PLACEHOLDER MOVIE MENU (Students will implement)
    private void movieMenu() {
        System.out.println("\n=== Movie Menu ===");
        System.out.println("TODO: Movie menu implementation");
        System.out.println("This menu should include:");
        System.out.println("- View all movies");
        System.out.println("- View available movies");
        System.out.println("- Search movies by title");
        System.out.println("- Search movies by director");
        System.out.println("- Search movies by genre");
        System.out.println("- Borrow movie");
        System.out.println("- Return movie");
        System.out.println("- Show movie duration");
        System.out.println();
        System.out.println("Press Enter to continue...");
        scanner.nextLine();

        // TODO: Students should implement movie-specific functionality here
        // Similar structure to bookMenu() but for Movie objects
        // Should handle Movie-specific fields like duration
    }

    // PLACEHOLDER MAGAZINE MENU (Students will implement)
    private void magazineMenu() {
        System.out.println("\n=== Magazine Menu ===");
        System.out.println("TODO: Magazine menu implementation");
        System.out.println("This menu should include:");
        System.out.println("- View all magazines");
        System.out.println("- View available magazines");
        System.out.println("- Search magazines by title");
        System.out.println("- Search magazines by publisher");
        System.out.println("- Search magazines by genre");
        System.out.println("- Borrow magazine");
        System.out.println("- Return magazine");
        System.out.println("- Show issue information");
        System.out.println();
        System.out.println("Press Enter to continue...");
        scanner.nextLine();

        // TODO: Students should implement magazine-specific functionality here
        // Similar structure to bookMenu() but for Magazine objects
        // Should handle Magazine-specific fields like issue number and publication date
    }

    // BOOK-SPECIFIC METHODS (Fully implemented as reference)
    private void viewAllBooks() {
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\n=== All Books ===");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void viewAvailableBooks() {
        List<Book> availableBooks = library.getAvailableBooks();
        if (availableBooks.isEmpty()) {
            System.out.println("No books currently available.");
            return;
        }

        System.out.println("\n=== Available Books ===");
        for (Book book : availableBooks) {
            System.out.println(book);
        }
    }

    private void searchBooks() {
        System.out.print("Enter search term for books (title, author, genre, or ISBN): ");
        String query = scanner.nextLine();

        List<Book> results = library.searchBooks(query);
        if (results.isEmpty()) {
            System.out.println("No books found matching your search.");
        } else {
            System.out.println("\n=== Book Search Results ===");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private void searchBooksByAuthor() {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        List<Book> results = library.searchByAuthor(author);
        if (results.isEmpty()) {
            System.out.println("No books found by author: " + author);
        } else {
            System.out.println("\n=== Books by " + author + " ===");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private void searchBooksByGenre() {
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        List<Item> results = library.searchByGenre(genre);
        if (results.isEmpty()) {
            System.out.println("No books found in genre: " + genre);
        } else {
            System.out.println("\n=== Books in " + genre + " ===");
            for (Item book : results) {
                System.out.println(book);
            }
        }
    }

    private void borrowSpecificBook() {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        if (library.borrowBook(memberId, isbn)) {
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Unable to borrow book. Please check member ID, ISBN, and book availability.");
        }
    }

    private void returnSpecificBook() {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        if (library.returnBook(memberId, isbn)) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Unable to return book. Please check member ID and ISBN.");
        }


    }
    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void searchAllItems() {
        System.out.print("Enter search term (title, creator, genre, or ID): ");
        String query = scanner.nextLine();

        List<Item> results = library.searchItems(query)

        if (results.isEmpty()) {
            System.out.println("No items found matching your search.");
        } else {
            System.out.println("\n=== Search Results ===");
            for (Item item : results) {
                System.out.println(item);
            }
        }
    }

    private void borrowItem() {
        logger.debug("Borrow item operation initiated");
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter item ID (ISBN for books, MVE for movies, MAG for magazines): ");
        String itemId = scanner.nextLine();

        if (library.borrowItem(memberId, itemId)) {
            System.out.println("Item borrowed successfully!");
        } else {
            System.out.println("Unable to borrow item. Please check member ID, item ID, and item availability.");
        }
    }

    private void returnItem() {
        logger.debug("Return item operation initiated");
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter item ID: ");
        String itemId = scanner.nextLine();

        if (library.returnItem(memberId, itemId)) {
            System.out.println("Item returned successfully!");
        } else {
            System.out.println("Unable to return item. Please check member ID and item ID.");
        }
    }

    private void registerMember() {
        logger.debug("Member registration initiated");
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        System.out.print("Enter member email: ");
        String email = scanner.nextLine();

        if (!Library.isValidEmail(email)) {
            System.out.println("Invalid email address.");
            return;
        }

        String memberId = Library.generateMemberId();
        Member member = new Member(memberId, name, email);

        if (library.addMember(member)) {
            System.out.println("Member registered successfully! Member ID: " + memberId);
        } else {
            System.out.println("Error registering member.");
        }
    }

    private void viewMemberInfo() {
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        Member member = library.getMember(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("\n=== Member Information ===");
        System.out.println(member);

        List<String> borrowedItems = member.getBorrowedBooks();
        if (!borrowedItems.isEmpty()) {
            System.out.println("\n=== Borrowed Items ===");
            for (String itemId : borrowedItems) {
                Item item = library.getItem(itemId);
                if (item != null) {
                    System.out.println("- " + item.getTitle() + " by " + item.getCreator() + " (" + item.getType() + ")");
                }
            }
        }
    }

    private void viewAllItems() {
        List<Item> items = library.getAllItems();

        if (items.isEmpty()) {
            System.out.println("No items in the library.");
            return;
        }

        System.out.println("\n=== All Items in Library ===");
        System.out.println("Books: " + items.stream().mapToInt(i -> i instanceof Book ? 1 : 0).sum());
        System.out.println("Movies: " + items.stream().mapToInt(i -> i instanceof Movie ? 1 : 0).sum());
        System.out.println("Magazines: " + items.stream().mapToInt(i -> i instanceof Magazine ? 1 : 0).sum());
        System.out.println();

        for (Item item : items) {
            System.out.println(item);
        }
    }

    private void saveAndExit() {
        logger.info("User initiated save and exit");
        try {
            library.saveToCSV();
            System.out.println("Data saved successfully. Goodbye!");
            logger.info("Library Management System shut down successfully");
        } catch (IOException e) {
            logger.error("Failed to save data during exit", e);
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}