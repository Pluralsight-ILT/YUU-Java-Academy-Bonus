# Bonus Feature Tickets - Advanced Challenges

## **Bonus Ticket #18: Singleton Pattern Implementation**
**Priority:** Medium  
**Component:** Service Layer  
**Difficulty:** ⭐⭐⭐

**Description:**
Convert the Library class to use the Singleton design pattern to ensure only one instance exists throughout the application lifecycle.

**Acceptance Criteria:**
- Library class implements Singleton pattern
- Constructor is private
- `getInstance()` method provides access to single instance
- All existing functionality remains unchanged
- Update LibrarySystem to use Singleton instance
- Thread-safe implementation (synchronized method)

**Implementation Hints:**
- Make constructor private
- Add static variable to hold single instance
- Add public static getInstance() method
- Initialize instance on first call (lazy initialization)

---

## **Bonus Ticket #19: Late Fee Calculator**
**Priority:** Low  
**Component:** Service & Model Layers  
**Difficulty:** ⭐⭐⭐

**Description:**
Implement a late fee calculation system for overdue items based on borrow duration and daily rates.

**Acceptance Criteria:**
- Add `borrowDate` field to Member's borrowed books tracking (use HashMap<String, String>)
- Each item type has different daily late fee rates ($0.50/day for books, $1.00/day for movies, $0.25/day for magazines)
- Add method `calculateLateFees(String memberId)` in Library class
- Display late fees in member information
- Update borrowed.csv format to include borrow dates

**Implementation Approach:**
- Store borrow date as String in format "YYYY-MM-DD"
- Calculate days overdue using simple date arithmetic
- Add static fee rates to each item type

---

## **Bonus Ticket #20: Custom Exception Hierarchy**
**Priority:** Low  
**Component:** All Layers  
**Difficulty:** ⭐⭐⭐

**Description:**
Create a custom exception hierarchy for library-specific errors with meaningful error messages.

**Acceptance Criteria:**
- Base `LibraryException` extends `Exception`
- Specific exceptions: `ItemNotFoundException`, `MemberNotFoundException`, `ItemAlreadyBorrowedException`, `ItemNotBorrowedException`
- Replace generic error returns with specific exceptions
- Include helpful error messages with item/member details
- Update all Library methods to throw appropriate exceptions

**Example Usage:**
```java
if (item == null) {
    throw new ItemNotFoundException("Item with ID " + itemId + " not found");
}
```

---

## **Bonus Ticket #21: Builder Pattern for Items**
**Priority:** Low  
**Component:** Model Layer  
**Difficulty:** ⭐⭐⭐

**Description:**
Implement the Builder pattern for creating complex Item objects with optional fields like ratings, descriptions, or editions.

**Acceptance Criteria:**
- Create `BookBuilder`, `MovieBuilder`, `MagazineBuilder` classes
- Support optional fields: description, rating (1-5), language, edition
- Fluent interface with method chaining
- `build()` method returns the constructed object
- Validation in builder methods (e.g., rating between 1-5)

**Example Usage:**
```java
Book book = new BookBuilder("ISBN123", "Title", "Author", "Fiction")
    .withDescription("A great book")
    .withRating(4)
    .withLanguage("English")
    .build();
```

---

## **Bonus Ticket #22: Simple Factory for Item Creation**
**Priority:** Medium  
**Component:** Service Layer  
**Difficulty:** ⭐⭐⭐

**Description:**
Create a factory class to centralize item creation logic and simplify adding new item types.

**Acceptance Criteria:**
- `ItemFactory` class with static methods
- `createBook()`, `createMovie()`, `createMagazine()` methods
- Factory handles item creation logic and validation
- Update CSV loading to use factory methods
- Easy to add new item types without changing Library class

**Benefits:**
- Centralized item creation
- Easier to maintain and extend
- Consistent validation across item types

---

## **Bonus Ticket #23: Template Method for Search Operations**
**Priority:** Medium  
**Component:** Service Layer  
**Difficulty:** ⭐⭐⭐⭐

**Description:**
Implement Template Method pattern to standardize search operations across different item types.

**Acceptance Criteria:**
- Abstract `SearchTemplate` class with template method
- Concrete implementations: `BookSearcher`, `MovieSearcher`, `MagazineSearcher`
- Common search steps: validate input, perform search, filter results, sort results
- Each searcher implements type-specific search logic
- Library uses appropriate searcher for each item type

**Template Steps:**
1. Validate search query
2. Get items of specific type
3. Apply search criteria
4. Sort results (alphabetical by title)
5. Return results

---

## **Bonus Ticket #24: Simple Observer for Logging**
**Priority:** Medium  
**Component:** Service Layer  
**Difficulty:** ⭐⭐⭐

**Description:**
Implement a simple observer pattern to notify when library operations occur (without using interfaces yet).

**Acceptance Criteria:**
- `LibraryObserver` class with methods: `onItemBorrowed()`, `onItemReturned()`, `onMemberRegistered()`
- Library class maintains list of observers
- Methods to add/remove observers
- Library notifies observers when operations complete
- Create `ConsoleObserver` that prints notifications

**Implementation:**
- Use ArrayList to store observers
- Call observer methods after successful operations
- Pass relevant data (member name, item title) to observers

---

## **Bonus Ticket #25: Immutable Value Objects**
**Priority:** High  
**Component:** Model Layer  
**Difficulty:** ⭐

**Description:**
Create immutable value objects for common data like Address, ContactInfo, or ItemDetails while keeping main classes mutable.

**Acceptance Criteria:**
- Create immutable `ItemDetails` class (title, creator, genre, description)
- Create immutable `MemberContact` class (name, email, phone, address)
- All fields final, no setters
- Update Item and Member classes to use these value objects
- Ensure toString(), equals(), and hashCode() work correctly

**Benefits:**
- Practice immutability concepts
- Better data encapsulation
- Thread-safe value objects

---

## **Bonus Ticket #26: State Pattern for Item Availability**
**Priority:** High  
**Component:** Model Layer  
**Difficulty:** ⭐⭐⭐⭐

**Description:**
Implement State pattern to manage item availability states (Available, Borrowed, Reserved, Lost).

**Acceptance Criteria:**
- `ItemState` abstract class with methods: `borrow()`, `return()`, `getStatus()`
- Concrete states: `AvailableState`, `BorrowedState`, `ReservedState`, `LostState`
- Item class delegates state-specific behavior to current state
- State transitions follow business rules
- Update UI to show all possible states

**State Transitions:**
- Available → Borrowed (when borrowed)
- Borrowed → Available (when returned)
- Available → Reserved (future feature)
- Any → Lost (administrative action)

---
