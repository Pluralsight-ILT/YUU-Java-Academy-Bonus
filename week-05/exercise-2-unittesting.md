# **Exercises – Unit Testing Fundamentals**

In these exercises you will create comprehensive unit tests for a Calculator class. You will practice writing test methods, using assertions, and testing both normal cases and edge cases.

Remember to create your projects in the pluralsight/workbook-4 folder.

---

## **EXERCISE 2**

Create a Calculator class and write thorough unit tests to verify its functionality. Learn to test normal operations, edge cases, and error conditions using proper testing methodology.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a `Calculator` class with methods: add(), subtract(), multiply(), divide()
- Create a `CalculatorTest` class with comprehensive test methods
- Write tests for normal cases, boundary conditions, and negative numbers
- Use descriptive test method names that explain what is being tested
- Include tests for various number combinations: positive, negative, zero
- Test edge cases like adding zero, subtracting larger from smaller numbers
- Demonstrate how tests help catch bugs and validate functionality
- Show both passing and failing test scenarios

**Expected Test Structure:**
```java
public class CalculatorTest {
    
    @Test
    public void testAddPositiveNumbers() {
        // Test normal addition
    }
    
    @Test
    public void testSubtractNegativeResult() {
        // Test subtraction resulting in negative
    }
    
    @Test
    public void testMultiplyByZero() {
        // Test multiplication edge case
    }
}
```

**Expected Output Example:**
```
Running Calculator Tests...

✓ testAddPositiveNumbers - PASSED
✓ testSubtractNegativeResult - PASSED  
✓ testMultiplyByZero - PASSED
✓ testDivideBasicNumbers - PASSED
✗ testAddNegativeNumbers - FAILED: Expected 5, but got 3

Tests run: 5, Passed: 4, Failed: 1
```