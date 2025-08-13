# **Exercises – Static Methods and Variables**

In these exercises you will create a Library System that demonstrates static methods and variables. You will practice class-level data sharing, utility methods, and understanding when to use static vs instance members.

Remember to create your projects in the pluralsight/workbook-4 folder.

---

## **EXERCISE 3**

Create a Library Management System that uses static methods for utility functions and static variables to track system-wide data. Demonstrate the difference between static and instance members.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a `Book` class with instance variables: title, author, isbn, isCheckedOut
- Create a `LibraryUtils` class with static methods: generateISBN(), validateISBN(), formatTitle()
    - `generateISBN()` should create a random 13-digit ISBN (978 prefix + 10 random digits)
    - Use this method: `return "978" + String.valueOf((long)(Math.random() * 1000000000L));`
    - `validateISBN()` should check if ISBN starts with "978" and has 13 digits total
    - `formatTitle()` should convert titles to uppercase for consistent display
- Add static variables to track: totalBooks, totalCheckouts, libraryName
- Implement static methods: getLibraryStats(), resetCounters(), setLibraryName()
- Create instance methods in Book class that use the static utility methods
- Demonstrate calling static methods without creating objects
- Show how static variables are shared across all instances

**Expected Output Example:**
```
=== Library System Demo ===

Library: Dormont Public Library
Total books in system: 0

Creating books using static utility methods...
Generated ISBN: 978-1234567890
Formatted title: "BREAKFAST OF CHAMPIONS"

Book created: Breakfast of Champions by Kurt Vonnegut
Book created: 1984 by George Orwell

Library Statistics:
- Total books: 2
- Total checkouts: 0
- Library name: Dormont Public Library

Checking out books...
"Breakfast of Champions" checked out successfully.

Updated Statistics:
- Total books: 2  
- Total checkouts: 1

Calling static methods without objects:
LibraryUtils.validateISBN("978-1234567890"): true
LibraryUtils.formatTitle("brave new world"): "BRAVE NEW WORLD"
```