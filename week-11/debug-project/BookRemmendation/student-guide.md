# Student Guide - Book Recommendation API

## Progress Tracker

### Phase 1: Bug Fixes (Complete First)
- [ ] **Bug #1** - Controller Not Being Detected
- [ ] **Bug #2** - SQL Query Column Name Error
- [ ] **Bug #3** - Path Variable Binding Issue
- [ ] **Bug #4** - PreparedStatement Parameter Error
- [ ] **Bug #5** - Database Connection Resource Leak
- [ ] **Bug #6** - JSON Serialization Problem

---

## Project Overview

The Book Recommendation API is a Spring Boot REST application that manages a curated collection of mind-opening and entertaining books. The system uses MySQL database with JDBC for data access and follows the MVC pattern.

### Key Features
- **REST API Endpoints**: CRUD operations for books
- **MySQL Integration**: Database storage with JDBC operations
- **Genre-Based Search**: Find books by category
- **Rating System**: Update and track book ratings
- **Curated Collection**: Educational and entertaining books

### Current State
The application has **6 bugs** that prevent it from working correctly. The bugs cover Spring Boot annotations, JDBC operations, and REST API functionality.

---

## Getting Started

### 1. Project Structure
Your project contains:
- **Book.java** - Data model class
- **BookController.java** - REST API endpoints
- **BookService.java** - Business logic with JDBC operations
- **DatabaseConfig.java** - DataSource configuration
- **books_db.sql** - Database schema and sample data

### 2. Database Setup
1. **Create MySQL database**: `book_recommendations`
2. **Run the SQL script** to create tables and insert sample data
3. **Update application.properties** with your MySQL credentials

### 3. Expected Functionality
When working correctly, the API should:
- Return all books via GET /api/books
- Filter books by genre via GET /api/books/genre/{genre}
- Retrieve specific books via GET /api/books/{id}
- Add new books via POST /api/books
- Update ratings via PUT /api/books/{id}/rating

---

## IntelliJ IDEA Setup

### Opening the Project
1. **Import as Maven/Gradle project** in IntelliJ IDEA
2. **Configure MySQL connection** in application.properties
3. **Run the SQL script** to set up your database
4. **Start the Spring Boot application**

### Testing the API
Use Postman, curl, or browser to test endpoints:
```
GET http://localhost:8080/api/books
GET http://localhost:8080/api/books/genre/Science
GET http://localhost:8080/api/books/1
```

---

## Database Schema

### Books Table Structure
```sql
books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    rating DECIMAL(2,1) DEFAULT 0.0,
    year_published INT,
    description TEXT,
    is_available BOOLEAN DEFAULT true
)
```

### Sample Data Includes
- **Fiction**: Breakfast of Champions, The Island of Dr. Moreau
- **Science**: A Brief History of Time, Cosmos
- **Physics**: The Elegant Universe, Parallel Worlds
- **Philosophy**: Sapiens, The Selfish Gene
- **Mathematics**: The Code Book, Flatland

---

## Understanding the Bugs

### Categories of Issues
1. **Spring Boot Annotations** - Missing or incorrect controller annotations
2. **JDBC Operations** - SQL syntax and parameter binding errors
3. **Resource Management** - Database connection handling
4. **REST API Configuration** - Path mapping and parameter binding
5. **JSON Serialization** - Model class getter/setter issues

### Debugging Strategy
1. **Check Application Startup** - Look for Spring Boot component scanning issues
2. **Test Database Connection** - Verify MySQL connectivity and data
3. **Test API Endpoints** - Use Postman or browser to test each endpoint
4. **Review JDBC Code** - Check SQL queries and parameter binding
5. **Monitor Resources** - Ensure database connections are properly closed

---

## Expected Working Output

### GET /api/books
```json
[
    {
        "id": 1,
        "title": "Breakfast of Champions",
        "author": "Kurt Vonnegut",
        "genre": "Fiction",
        "rating": 4.3,
        "yearPublished": 1973,
        "description": "A satirical novel about American society",
        "available": true
    },
    ...
]
```

### GET /api/books/genre/Science
```json
[
    {
        "id": 3,
        "title": "Cosmos",
        "author": "Carl Sagan",
        "genre": "Science",
        "rating": 4.8,
        "yearPublished": 1980,
        "description": "Exploration of the universe and our place in it",
        "available": true
    }
]
```

---

## Key Concepts Practiced

### Spring Boot Topics
- **@RestController** and **@RequestMapping** annotations
- **@PathVariable** and **@RequestBody** parameter binding
- **HTTP method mappings** (@GetMapping, @PostMapping, @PutMapping)
- **Component scanning** and dependency injection

### JDBC Topics
- **DataSource** configuration and usage
- **PreparedStatement** with parameter binding
- **ResultSet** processing and object mapping
- **Resource management** with try-with-resources
- **SQL query construction** and execution

---

## Completion Checklist

Before submitting your work, ensure:
- [ ] Application starts without errors
- [ ] All REST endpoints respond correctly
- [ ] Database queries execute successfully
- [ ] JSON responses are properly formatted
- [ ] Database connections are properly closed
- [ ] All bugs have been identified and fixed
- [ ] API handles both valid and invalid requests appropriately

---

## Getting Help

If you encounter issues:
1. **Check Spring Boot logs** for startup errors and component scanning issues
2. **Verify database connection** and ensure MySQL is running
3. **Test SQL queries** directly in MySQL Workbench or command line
4. **Use debugging tools** to trace JDBC operations
5. **Check HTTP status codes** and error responses from endpoints
6. **Review annotation usage** for proper Spring Boot configuration

### Common Issues
- **404 errors**: Check @RestController and @RequestMapping annotations
- **500 errors**: Look for SQL syntax errors or parameter binding issues
- **Connection errors**: Verify MySQL credentials and database existence
- **JSON errors**: Ensure model classes have proper getters and setters

Remember: This project combines Spring Boot web development with JDBC database operations. Focus on understanding the MVC pattern and proper resource management!