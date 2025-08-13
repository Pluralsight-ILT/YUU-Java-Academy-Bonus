# Library Management System - Bug Tickets

## **Unit Test Bugs**

### **Ticket #1: Book Equality Test Failing**
**Priority:** High  
**Component:** Model Layer (Book class)

**Description:**
The BookTest is failing when testing book equality. Books with the same ISBN should be considered equal, but the test is failing even when comparing books with identical ISBNs.

**Steps to Reproduce:**
1. Run BookTest.java
2. Observe the testBookEquality() test failure

**Expected Behavior:**
Two books with the same ISBN should be equal regardless of other properties (title, author, genre).

**Actual Behavior:**
Books with the same ISBN are being treated as not equal.

---

### **Ticket #2: Member Can Borrow Same Book Multiple Times**
**Priority:** High  
**Component:** Model Layer (Member class)

**Description:**
The member borrowing system is allowing members to borrow the same book multiple times. The MemberTest is failing because duplicate borrowing should be prevented.

**Steps to Reproduce:**
1. Run MemberTest.java
2. Check the testBorrowBook() test
3. Notice that a member can borrow the same ISBN twice

**Expected Behavior:**
A member should not be able to borrow the same book twice. The second attempt should return false.

**Actual Behavior:**
Members can borrow the same book multiple times.

---

### **Ticket #3: Book Borrowing Always Fails**
**Priority:** High  
**Component:** Service Layer (Library class)

**Description:**
The LibraryTest is failing on book borrowing operations. When attempting to borrow available books, the operation fails even though the book should be available.

**Steps to Reproduce:**
1. Run LibraryTest.java
2. Check the testBorrowItem() test
3. Try borrowing an available book through the library system

**Expected Behavior:**
Available books should be successfully borrowed and marked as unavailable.

**Actual Behavior:**
Borrowing fails even for available books.

---

### **Ticket #4: Movie Borrow Duration Incorrect**
**Priority:** Medium  
**Component:** Model Layer (Movie class)

**Description:**
The MovieTest is failing when checking the borrow duration for movies. Movies should have a 3-day borrowing period, but the test indicates a different value is being returned.

**Steps to Reproduce:**
1. Run MovieTest.java
2. Check the testMovieCreation() test
3. Verify the getBorrowDuration() assertion

**Expected Behavior:**
Movies should have a borrow duration of 3 days.

**Actual Behavior:**
Movies are returning an incorrect borrow duration value.

---

## **Search and Display Bugs**

### **Ticket #5: Search Function Case Sensitivity Issue**
**Priority:** Medium  
**Component:** Service Layer (Library class)

**Description:**
Users report that searching for items is not working as expected. Searching for "java" returns no results, but searching for "Java" (capitalized) finds books. The search should be case-insensitive.

**Steps to Reproduce:**
1. Run the application
2. Search for "programming" (lowercase)
3. Search for "Programming" (capitalized)
4. Notice different results

**Expected Behavior:**
Search should be case-insensitive. Both "java" and "Java" should return the same results.

**Actual Behavior:**
Search is case-sensitive, requiring exact case matching.

---

### **Ticket #6: Genre Search Returns Too Many Results**
**Priority:** Medium  
**Component:** Service Layer (Library class)

**Description:**
When searching by genre, the system returns more results than expected. For example, searching for "Science" returns both "Science" and "Science Fiction" books.

**Steps to Reproduce:**
1. Search for genre "Science"
2. Notice that "Science Fiction" books are also returned

**Expected Behavior:**
Genre search should return exact matches only. Searching for "Science" should not return "Science Fiction" books.

**Actual Behavior:**
Genre search returns partial matches, including books whose genre contains the search term.

---

## **Syntax and Compilation Bugs**

### **Ticket #7: Compilation Error in UI Layer**
**Priority:** Critical  
**Component:** UI Layer (LibrarySystem class)

**Description:**
The application fails to compile. There appears to be a syntax error in the searchAllItems() method that prevents the entire project from building.

**Steps to Reproduce:**
1. Attempt to compile the project
2. Check compiler error messages

**Expected Behavior:**
The project should compile without syntax errors.

**Actual Behavior:**
Compilation fails with syntax error in searchAllItems() method.

---

### **Ticket #8: Magazine Constructor Type Error**
**Priority:** High  
**Component:** Model Layer (Magazine class)

**Description:**
There's a compilation error when trying to create Magazine objects. The constructor seems to have an incorrect parameter type that doesn't match the expected String ID format.

**Steps to Reproduce:**
1. Try to compile the project
2. Notice the error in Magazine constructor calls
3. Check the parameter types in the constructor

**Expected Behavior:**
Magazine constructor should accept String parameters for IDs, consistent with other item types.

**Actual Behavior:**
Magazine constructor has incorrect parameter type causing compilation errors.

---

### **Ticket #9: Missing Import in Test Class**
**Priority:** Medium  
**Component:** Test Layer (LibraryTest class)

**Description:**
The LibraryTest class has a compilation error related to a missing import statement. The List interface is being used but not properly imported.

**Steps to Reproduce:**
1. Try to compile the test classes
2. Check the LibraryTest.java file
3. Notice the compilation error related to List usage

**Expected Behavior:**
All necessary imports should be present for the test class to compile.

**Actual Behavior:**
Missing import statement prevents test compilation.

---

## **Testing Instructions**

1. **For Unit Test Bugs (1-4):** Run the respective test classes and fix the failing tests
2. **For Search Bugs (5-6):** Test the search functionality manually through the UI
3. **For Syntax Bugs (7-9):** Attempt to compile the project and fix compilation errors

**Verification:** After fixing each bug, ensure that:
- All unit tests pass
- Search functionality works as expected
- The project compiles without errors
- Manual testing confirms the fixes work correctly