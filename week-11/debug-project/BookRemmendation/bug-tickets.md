# Book Recommendation API - Bug Tickets

## **Spring Boot Configuration Issues**

### **Bug #1: Controller Not Being Detected**
**Priority:** Critical  
**Component:** BookController

**Description:**
The Spring Boot application starts successfully, but the REST endpoints are not accessible. When trying to access any API endpoint, a 404 error is returned, suggesting that the controller is not being detected by Spring's component scanning.

**Steps to Reproduce:**
1. Start the Spring Boot application
2. Try to access http://localhost:8080/api/books
3. Receive 404 Not Found error

**Expected Behavior:**
Controller should be detected and endpoints should be accessible.

**Actual Behavior:**
All API endpoints return 404 errors despite successful application startup.

---

### **Bug #2: SQL Query Column Name Error**
**Priority:** High  
**Component:** BookService

**Description:**
When attempting to retrieve books from the database, the application throws a SQL exception indicating that a column doesn't exist. The error suggests there's a mismatch between the column names used in the SQL query and the actual database schema.

**Steps to Reproduce:**
1. Access any endpoint that retrieves book data
2. Check application logs for SQL exceptions
3. Notice column name errors in database queries

**Expected Behavior:**
SQL queries should use correct column names that match the database schema.

**Actual Behavior:**
SQL exception thrown due to incorrect column name reference.

---

## **REST API Parameter Issues**

### **Bug #3: Path Variable Binding Issue**
**Priority:** High  
**Component:** BookController

**Description:**
The endpoint for retrieving books by genre is not working correctly. The path variable for genre is not being properly bound to the method parameter, causing null or incorrect values to be passed to the service layer.

**Steps to Reproduce:**
1. Try to access http://localhost:8080/api/books/genre/Science
2. Notice that the genre parameter is not being captured correctly
3. Check if the method receives the expected genre value

**Expected Behavior:**
Path variable should be properly bound to method parameter.

**Actual Behavior:**
Genre parameter is null or not correctly extracted from the URL path.

---

### **Bug #4: PreparedStatement Parameter Error**
**Priority:** Medium  
**Component:** BookService

**Description:**
Database queries that use PreparedStatement parameters are failing with an index out of bounds exception. The error indicates that parameter indices in the PreparedStatement are incorrect.

**Steps to Reproduce:**
1. Execute any database operation that uses PreparedStatement with parameters
2. Check logs for parameter index errors
3. Notice SQLException related to parameter binding

**Expected Behavior:**
PreparedStatement parameters should be bound correctly using proper indices.

**Actual Behavior:**
SQLException thrown due to incorrect parameter index usage.

---

## **Resource Management Issues**

### **Bug #5: Database Connection Resource Leak**
**Priority:** Medium  
**Component:** BookService

**Description:**
Database connections are not being properly closed after use, leading to resource leaks. This can cause the application to run out of available connections and fail under load.

**Steps to Reproduce:**
1. Make multiple API calls to database-dependent endpoints
2. Monitor database connection pool or check for resource warnings
3. Notice that connections are not being released properly

**Expected Behavior:**
Database connections should be automatically closed using try-with-resources or similar patterns.

**Actual Behavior:**
Database connections remain open, causing resource leaks.

---

### **Bug #6: JSON Serialization Problem**
**Priority:** Medium  
**Component:** Book Model

**Description:**
When the API attempts to return book data as JSON, the response is empty or missing expected fields. This suggests an issue with JSON serialization of the Book model class.

**Steps to Reproduce:**
1. Successfully call any GET endpoint that should return book data
2. Notice that JSON response is empty or missing fields
3. Check that database query returns data but JSON conversion fails

**Expected Behavior:**
Book objects should be properly serialized to JSON with all fields included.

**Actual Behavior:**
JSON response is empty or missing expected book properties.

---

## **Testing Instructions**

### **For Spring Boot Issues (1, 3):**
1. Check controller class annotations and method mappings
2. Verify path variable annotations and parameter names
3. Test endpoint accessibility and parameter binding

### **For Database Issues (2, 4, 5):**
1. Review SQL queries and column names against database schema
2. Check PreparedStatement parameter indices and binding
3. Verify try-with-resources usage for connection management

### **For Serialization Issues (6):**
1. Check model class for proper getter methods
2. Test JSON serialization with simple objects
3. Verify that all required fields have appropriate accessors

**Verification Scenarios:**
- **Basic GET request**: Retrieve all books successfully
- **Filtered request**: Get books by specific genre
- **Parameter binding**: Extract path variables correctly
- **Database operations**: Execute queries without resource leaks
- **JSON response**: Return properly formatted book data

**Final Test:** All endpoints should work correctly, return proper JSON responses, and manage database resources appropriately.