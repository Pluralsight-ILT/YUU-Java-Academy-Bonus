# Library Management System - Feature Tickets

## **Movie Search Features**

### **Ticket #10: Implement Movie-Specific Methods in Library Service**
**Priority:** High  
**Component:** Service Layer (Library class)  
**Story Points:** 5

**Description:**
As a librarian, I want to have movie-specific search and management methods so that I can help patrons find movies more efficiently.

**Acceptance Criteria:**
- Implement `getAllMovies()` method that returns only Movie objects
- Implement `getAvailableMovies()` method that returns only available Movie objects
- Implement `searchMoviesByDirector(String director)` method for director-specific searches
- All methods should follow the same pattern as the existing book methods

**Technical Notes:**
- Filter items collection to return only Movie instances
- Use instanceof checks to identify Movie objects
- Ensure proper casting when returning Movie lists

---

### **Ticket #11: Create Movie Search Methods in Library Service**
**Priority:** High  
**Component:** Service Layer (Library class)  
**Story Points:** 3

**Description:**
As a patron, I want to search specifically within the movie collection so that I can find movies without getting books or magazines in my results.

**Acceptance Criteria:**
- Implement `searchMovies(String query)` method that searches only movies by title, director, genre, or ID
- Method should be case-insensitive
- Return List<Movie> containing matching results

**Technical Notes:**
- Follow the pattern of existing `searchBooks()` method
- Filter the general item search results to return only Movie objects

---

### **Ticket #12: Implement Complete Movie Menu System**
**Priority:** Medium  
**Component:** UI Layer (LibrarySystem class)  
**Story Points:** 8

**Description:**
As a user, I want a dedicated movie menu with full functionality so that I can browse, search, and manage movies separately from other items.

**Acceptance Criteria:**
- Replace the TODO placeholder in `movieMenu()` with a complete implementation
- Include menu options for:
    1. View All Movies
    2. View Available Movies
    3. Search Movies
    4. Search by Director
    5. Search by Genre
    6. Borrow Movie
    7. Return Movie
    8. Back to Main Menu
- Implement all supporting methods (e.g., `viewAllMovies()`, `searchMoviesByDirector()`, etc.)
- Display movie duration in the output
- Handle user input validation

**Technical Notes:**
- Follow the same structure as the working `bookMenu()` implementation
- Use the movie-specific methods from Library service
- Ensure proper error handling for invalid inputs

---

## **Magazine Search Features**

### **Ticket #13: Implement Magazine-Specific Methods in Library Service**
**Priority:** High  
**Component:** Service Layer (Library class)  
**Story Points:** 5

**Description:**
As a librarian, I want to have magazine-specific search and management methods so that I can help patrons find magazines more efficiently.

**Acceptance Criteria:**
- Implement `getAllMagazines()` method that returns only Magazine objects
- Implement `getAvailableMagazines()` method that returns only available Magazine objects
- Implement `searchMagazinesByPublisher(String publisher)` method for publisher-specific searches
- All methods should follow the same pattern as the existing book methods

**Technical Notes:**
- Filter items collection to return only Magazine instances
- Use instanceof checks to identify Magazine objects
- Ensure proper casting when returning Magazine lists

---

### **Ticket #14: Create Magazine Search Methods in Library Service**
**Priority:** High  
**Component:** Service Layer (Library class)  
**Story Points:** 3

**Description:**
As a patron, I want to search specifically within the magazine collection so that I can find magazines without getting books or movies in my results.

**Acceptance Criteria:**
- Implement `searchMagazines(String query)` method that searches only magazines by title, publisher, genre, or ID
- Method should be case-insensitive
- Return List<Magazine> containing matching results

**Technical Notes:**
- Follow the pattern of existing `searchBooks()` method
- Filter the general item search results to return only Magazine objects

---

### **Ticket #15: Implement Complete Magazine Menu System**
**Priority:** Medium  
**Component:** UI Layer (LibrarySystem class)  
**Story Points:** 8

**Description:**
As a user, I want a dedicated magazine menu with full functionality so that I can browse, search, and manage magazines separately from other items.

**Acceptance Criteria:**
- Replace the TODO placeholder in `magazineMenu()` with a complete implementation
- Include menu options for:
    1. View All Magazines
    2. View Available Magazines
    3. Search Magazines
    4. Search by Publisher
    5. Search by Genre
    6. Borrow Magazine
    7. Return Magazine
    8. Back to Main Menu
- Implement all supporting methods (e.g., `viewAllMagazines()`, `searchMagazinesByPublisher()`, etc.)
- Display issue number and publication date in the output
- Handle user input validation

**Technical Notes:**
- Follow the same structure as the working `bookMenu()` implementation
- Use the magazine-specific methods from Library service
- Ensure proper error handling for invalid inputs

---

## **Additional Enhancement Features**

### **Ticket #16: Add Duration Display for Movie Operations**
**Priority:** Low  
**Component:** UI Layer (LibrarySystem class)  
**Story Points:** 2

**Description:**
As a user, I want to see movie duration information when viewing or searching movies so that I can make informed borrowing decisions.

**Acceptance Criteria:**
- When displaying movies in any menu (view all, search results, etc.), include the duration
- Format: "Title by Director [Genre] (XXX min) - Available/Borrowed"
- Ensure duration is displayed consistently across all movie-related operations

---

### **Ticket #17: Add Issue Information Display for Magazine Operations**
**Priority:** Low  
**Component:** UI Layer (LibrarySystem class)  
**Story Points:** 2

**Description:**
As a user, I want to see issue number and publication date when viewing or searching magazines so that I can find the specific issue I need.

**Acceptance Criteria:**
- When displaying magazines in any menu, include issue number and publication date
- Format: "Title by Publisher [Genre] Issue: XXX (YYYY-MM-DD) - Available/Borrowed"
- Ensure issue information is displayed consistently across all magazine-related operations

---

## **Implementation Guidelines**

### **Development Approach:**
1. Start with service layer methods (Tickets #10, #11, #13, #14)
2. Then implement UI layer menus (Tickets #12, #15)
3. Finally add enhancement features (Tickets #16, #17)

### **Testing Requirements:**
- Write unit tests for all new service methods
- Test menu navigation manually
- Verify search functionality works correctly
- Ensure proper error handling

### **Code Quality:**
- Follow existing code patterns and naming conventions
- Add appropriate logging statements
- Include proper error handling and validation
- Maintain consistency with existing book implementation

### **Acceptance Criteria for All Tickets:**
- Code compiles without errors
- New functionality integrates smoothly with existing system
- All existing tests continue to pass
- New features work as specified in acceptance criteria