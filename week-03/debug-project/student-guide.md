# Student Guide - Student Course Registration System

## Progress Tracker

### Phase 1: Bug Fixes (Complete First)
- [ ] **Ticket #1** - Student Equality Comparison Issue
- [ ] **Ticket #2** - Course Enrollment Removal Error
- [ ] **Ticket #3** - File Resource Management Problem
- [ ] **Ticket #4** - Course Lookup Failure
- [ ] **Ticket #5** - Student Constructor Assignment Error
- [ ] **Ticket #6** - Method Logic Return Issue
- [ ] **Ticket #7** - CSV Data Parsing Edge Case
- [ ] **Ticket #8** - Collection Modification During Iteration

---

## Project Overview

The Student Course Registration System is a Java application demonstrating object-oriented programming, collections, and file I/O. The system manages student enrollments in courses with persistent data storage using CSV files.

### Key Features
- **Student Management**: Create and track student profiles
- **Course Management**: Define courses with enrollment limits
- **Registration System**: Enroll/drop students from courses
- **Data Persistence**: Save and load data from CSV files
- **Enrollment Tracking**: Monitor course capacity and student schedules
- **Reporting**: Generate enrollment reports and statistics

### Current Functionality
The application should:
1. Load existing student and course data from CSV files
2. Allow new student registration
3. Enable course enrollment and dropping
4. Track enrollment limits and conflicts
5. Save all changes back to CSV files
6. Generate enrollment reports

---

## Getting Started

### 1. Project Structure
Your project contains three files:
- **Student.java** - Student class with profile management
- **Course.java** - Course class with enrollment tracking
- **RegistrationSystem.java** - Main application with file operations

### 2. Understanding the Current State
The application currently has:
- **No 8 subtle bugs** causing incorrect behavior
- **No Object comparison issues** affecting collections
- **No File handling problems** causing data loss
- **No Collection modification errors** during processing

### 3. Sample Data Files
Create a `data` folder in your project root and add the provided CSV files:
- **students.csv** - Contains sample student records
- **courses.csv** - Contains sample course information

---

## IntelliJ IDEA Setup

### Opening the Project
1. **Create New Project** in IntelliJ IDEA
2. **Copy all three Java files** to your src folder
3. **Create a data folder** in your project root
4. **Add the provided CSV files** (students.csv and courses.csv) to the data folder

---

## Running the Application

### Expected Sample Run
```
=== Student Course Registration System ===
Loading data from files...
Loaded 5 students and 8 courses.

Menu:
1. Register New Student
2. Enroll in Course
3. Drop Course
4. View Student Schedule
5. View Course Enrollment
6. Save and Exit

Enter choice: 2
Enter student ID: STU001
Enter course code: CS101

Enrollment successful!
Student John Smith enrolled in Introduction to Programming.
Remaining spots: 18/20

Menu:
1. Register New Student
...
```

---

## Understanding the System

### Data Files Structure

**students.csv**
```csv
StudentID,Name,Email,Major
STU001,John Smith,john@email.com,Computer Science
STU002,Jane Doe,jane@email.com,Mathematics
```

**courses.csv**
```csv
CourseCode,Title,Instructor,MaxEnrollment,CurrentEnrollment
CS101,Introduction to Programming,Dr. Johnson,20,2
MATH201,Calculus II,Prof. Williams,25,15
```

### Class Relationships
- **Student**: Manages student profile and enrolled courses
- **Course**: Tracks course details and enrollment list
- **RegistrationSystem**: Coordinates operations and file I/O

---

## Key Concepts Practiced

### Week 3 Topics Covered
- **Classes and Objects**: Student and Course class design
- **Collections**: ArrayList for enrollment, HashMap for lookups
- **File I/O**: BufferedReader/Writer for CSV processing
- **Exception Handling**: Try-catch blocks for file operations
- **Object Equality**: equals() and hashCode() methods
- **Method Design**: Return types, parameters, encapsulation

### Common Patterns
- Object creation and initialization
- Collection manipulation and iteration
- File reading and writing with proper resource management
- Data validation and error handling
- Object comparison and collection membership

---

## Development Workflow

### Recommended Approach
1. **Understand the Classes**
   - Review each class structure and intended functionality
   - Understand the relationships between Student and Course

2. **Test Basic Operations**
   - Try creating students and courses
   - Test enrollment and dropping functionality

3. **Check File Operations**
   - Verify data loads correctly from CSV files
   - Ensure data saves properly when exiting

4. **Test Edge Cases**
   - Try enrolling in full courses
   - Test with duplicate enrollments
   - Verify proper error handling

### Testing Scenarios
- **Basic enrollment**: Student enrolls in available course
- **Capacity limits**: Try enrolling in full course
- **Duplicate enrollment**: Student tries to enroll twice
- **File persistence**: Data survives application restart
- **Invalid inputs**: Non-existent students/courses

---

## Expected Learning Outcomes

After completing this debug project, you should understand:
- Object-oriented design principles and class relationships
- Collection usage for managing object relationships
- File I/O operations with proper resource management
- Exception handling for robust applications
- Object equality and collection membership
- Data persistence and CSV file processing

---

## Completion Checklist

Before submitting your work, ensure:
- [ ] All three classes compile without errors
- [ ] Students can be created and managed properly
- [ ] Course enrollment and dropping works correctly
- [ ] File loading and saving operates without data loss
- [ ] Enrollment limits are respected
- [ ] Duplicate enrollments are prevented
- [ ] CSV files are properly formatted and readable
- [ ] Application handles edge cases gracefully

---

## Getting Help

If you encounter issues:
1. **Test one class at a time** to isolate problems
2. **Check object equality** if collection operations fail
3. **Verify file operations** with simple test cases
4. **Use debugging tools** to trace object states
5. **Review collection iteration** for modification errors
6. **Check CSV parsing** with various data formats

### Debugging Tips
- Use println statements to trace object creation and modifications
- Test file operations with simple data first
- Verify collection contents before and after operations
- Check object equality with small test cases
- Monitor file handles and resource usage

Remember: This project demonstrates real-world object-oriented programming with persistent data storage. Focus on understanding object relationships and proper resource management!