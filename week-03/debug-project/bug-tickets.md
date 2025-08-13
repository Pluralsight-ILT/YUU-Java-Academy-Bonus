# Student Course Registration System - Bug Tickets

## **Object-Oriented Design Issues**

### **Ticket #1: Student Equality Comparison Issue**
**Priority:** High  
**Component:** Student Class

**Description:**
The system fails to properly identify duplicate students in collections. When checking if a student is already enrolled in a course, the comparison doesn't work as expected, allowing duplicate enrollments.

**Steps to Reproduce:**
1. Create a student and enroll in a course
2. Attempt to enroll the same student in the same course again
3. Observe that duplicate enrollment is allowed

**Expected Behavior:**
Students should be properly compared for equality to prevent duplicate enrollments.

**Actual Behavior:**
Student objects are not being recognized as equal even when they represent the same student.

---

### **Ticket #2: Course Enrollment Removal Error**
**Priority:** High  
**Component:** Course Class

**Description:**
When a student drops a course, the operation appears to complete successfully, but the student remains enrolled. The removal process from the enrollment list is not working correctly.

**Steps to Reproduce:**
1. Enroll a student in a course
2. Attempt to drop the student from the course
3. Check the course enrollment list - student is still present

**Expected Behavior:**
Student should be completely removed from the course enrollment list when dropping.

**Actual Behavior:**
Drop operation reports success but student remains in the enrollment list.

---

## **File I/O and Resource Management**

### **Ticket #3: File Resource Management Problem**
**Priority:** Medium  
**Component:** File Operations

**Description:**
The application occasionally fails to save data properly or reports file access issues. This appears to be related to improper resource management during file operations.

**Steps to Reproduce:**
1. Run the application multiple times
2. Make several enrollment changes
3. Notice that data sometimes isn't saved or file access errors occur

**Expected Behavior:**
File operations should complete reliably with proper resource cleanup.

**Actual Behavior:**
Intermittent file access issues and potential data loss due to resource management problems.

---

### **Ticket #4: Course Lookup Failure**
**Priority:** Medium  
**Component:** Registration System

**Description:**
The system sometimes fails to find courses that exist in the course collection. Course lookup operations return null even for valid course codes that should be found.

**Steps to Reproduce:**
1. Load courses from the CSV file
2. Attempt to enroll a student in a course that exists
3. Notice that course lookup fails for some valid courses

**Expected Behavior:**
All loaded courses should be findable by their course code.

**Actual Behavior:**
Course lookup fails to find some courses that exist in the collection.

---

## **Data Processing and Logic**

### **Ticket #5: Student Constructor Assignment Error**
**Priority:** High  
**Component:** Student Class

**Description:**
Student objects are not being created with the correct field values. Some fields appear to be assigned incorrectly or remain null after object creation.

**Steps to Reproduce:**
1. Create a new student with specific details
2. Display or access the student's information
3. Notice that some fields don't contain the expected values

**Expected Behavior:**
Student constructor should properly assign all provided values to the correct fields.

**Actual Behavior:**
Student objects have incorrect or missing field values after creation.

---

### **Ticket #6: Method Logic Return Issue**
**Priority:** Medium  
**Component:** Course/Student Methods

**Description:**
Some methods return incorrect boolean values or status information. Operations that should succeed return false, or vice versa, causing incorrect application behavior.

**Steps to Reproduce:**
1. Perform various enrollment operations
2. Check return values from enrollment/drop methods
3. Notice that success/failure indicators don't match actual results

**Expected Behavior:**
Methods should return accurate status information reflecting the actual operation outcome.

**Actual Behavior:**
Method return values don't correctly indicate operation success or failure.

---

## **Data Parsing and Edge Cases**

### **Ticket #7: CSV Data Parsing Edge Case**
**Priority:** Low  
**Component:** File Reading

**Description:**
The CSV parsing fails or produces incorrect results when encountering certain data formats or edge cases in the input files.

**Steps to Reproduce:**
1. Create CSV files with various data formats
2. Include edge cases like empty fields or special characters
3. Notice parsing failures or incorrect data loading

**Expected Behavior:**
CSV parser should handle common edge cases and data variations gracefully.

**Actual Behavior:**
Parser fails or produces incorrect results with certain data formats.

---

### **Ticket #8: Collection Modification During Iteration**
**Priority:** Medium  
**Component:** Registration System

**Description:**
The application crashes or behaves unexpectedly when modifying collections while iterating through them. This occurs during certain enrollment operations or data processing tasks.

**Steps to Reproduce:**
1. Perform operations that process multiple students or courses
2. Make changes to collections during these operations
3. Notice crashes or unexpected behavior

**Expected Behavior:**
Collection operations should complete without concurrent modification issues.

**Actual Behavior:**
Application crashes or behaves unpredictably due to collection modification during iteration.

---

## **Testing Instructions**

### **For Object Issues (1, 2, 5, 6):**
1. Create test scenarios with known objects
2. Verify object creation and comparison behavior
3. Test method return values against expected outcomes

### **For File Issues (3, 7):**
1. Test file operations with various data sets
2. Verify resource cleanup and data persistence
3. Test with edge cases in CSV format

### **For Collection Issues (4, 8):**
1. Test collection operations with multiple objects
2. Verify lookup operations work consistently
3. Test modification operations during iteration

**Verification Scenarios:**
- **Standard Operations**: Student registration, course enrollment, data persistence
- **Edge Cases**: Full courses, duplicate operations, invalid data
- **Stress Testing**: Multiple operations, large datasets, repeated save/load cycles

**Final Verification:** System should handle all standard operations correctly, persist data reliably, and prevent duplicate enrollments while maintaining data integrity.