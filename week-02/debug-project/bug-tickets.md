# Text Message Analytics Tool - Bug Tickets

## **Method Design Bugs**

### **Ticket #1: Method Return Type Mismatch Error**
**Priority:** Critical  
**Component:** Method Signatures

**Description:**
The `calculateAverageWords()` method has a return type mismatch. The method is declared to return one type but is actually returning a different type, causing a compilation error.

**Steps to Reproduce:**
1. Try to compile the project
2. Notice the compilation error in the `calculateAverageWords()` method
3. Check the method signature vs the return statement

**Expected Behavior:**
Method should return the correct data type that matches the calculation being performed.

**Actual Behavior:**
Compilation error due to return type mismatch between method signature and actual return value.

---

### **Ticket #2: String to Integer Conversion Error**
**Priority:** High  
**Component:** Timestamp Processing

**Description:**
The application crashes when trying to convert timestamp strings to integers. The wrong parsing method is being used, causing a runtime exception when processing time data.

**Steps to Reproduce:**
1. Run the application
2. Add a message with timestamp "1430"
3. Notice the application crashes with a parsing exception

**Expected Behavior:**
Timestamp strings like "1430" should be converted to integers (1430) for time calculations.

**Actual Behavior:**
Wrong parsing method causes NumberFormatException or similar runtime error.

---

## **Loop Logic Bugs**

### **Ticket #3: While Loop Infinite Loop Issue**
**Priority:** High  
**Component:** Menu System

**Description:**
The main menu while loop never terminates properly. The loop condition or update mechanism has an error that prevents the user from exiting the application normally.

**Steps to Reproduce:**
1. Run the application
2. Try to use the menu system
3. Notice the loop doesn't respond correctly or runs infinitely

**Expected Behavior:**
Menu should display options, accept user input, and allow proper exit when chosen.

**Actual Behavior:**
While loop either runs infinitely or doesn't update the loop control variable correctly.

---

### **Ticket #4: For Loop Array Bounds Error**
**Priority:** High  
**Component:** Message Processing

**Description:**
When processing multiple messages with a for loop, the application crashes with an ArrayIndexOutOfBoundsException. The loop bounds are incorrect, causing access beyond the array limits.

**Steps to Reproduce:**
1. Add multiple messages (3 or more)
2. Try to analyze or display the messages
3. Notice the array bounds exception occurs

**Expected Behavior:**
For loop should iterate through all messages without exceeding array boundaries.

**Actual Behavior:**
Loop attempts to access array elements beyond the valid range, causing runtime exception.

---

## **Variable Scope and Logic Bugs**

### **Ticket #5: Method Variable Scope Problem**
**Priority:** Medium  
**Component:** Statistics Calculation

**Description:**
Variables used in the statistics calculation methods are declared in the wrong scope, causing compilation errors or incorrect calculations. Variables may be inaccessible where they're needed.

**Steps to Reproduce:**
1. Try to compile the project
2. Check for variable scope errors in statistics methods
3. Notice variables are not accessible or incorrectly declared

**Expected Behavior:**
Variables should be declared in appropriate scope to be accessible where needed for calculations.

**Actual Behavior:**
Variable scope error prevents compilation or causes incorrect calculations.

---

### **Ticket #6: String Method Chain Error**
**Priority:** Medium  
**Component:** Message Analysis

**Description:**
String method chaining for text analysis has an error. Methods like `trim()`, `toLowerCase()`, or `split()` are being called incorrectly or in the wrong order, causing processing failures.

**Steps to Reproduce:**
1. Add a message with mixed case or extra spaces
2. Try to analyze the message content
3. Notice incorrect word counting or processing results

**Expected Behavior:**
String methods should properly clean and process message text for accurate analysis.

**Actual Behavior:**
String method chain error causes incorrect text processing or runtime exceptions.

---

### **Ticket #7: Loop Counter Logic Error**
**Priority:** Medium  
**Component:** Message Counting

**Description:**
The loop counter logic for tracking messages or statistics has an error. The counter is either not incrementing correctly, starting from the wrong value, or updating at the wrong time.

**Steps to Reproduce:**
1. Add several messages
2. Check the total message count in statistics
3. Notice the count is incorrect or off by one

**Expected Behavior:**
Message counters should accurately reflect the number of messages processed.

**Actual Behavior:**
Counter logic error results in incorrect totals or off-by-one errors.

---

### **Ticket #8: Missing Type Conversion Validation**
**Priority:** Low  
**Component:** Input Validation

**Description:**
The application doesn't properly validate string inputs before attempting type conversion. Invalid timestamp formats or numeric inputs cause the application to crash instead of handling errors gracefully.

**Steps to Reproduce:**
1. Enter an invalid timestamp (like "abc" instead of "1430")
2. Notice the application crashes instead of handling the error
3. Test with other invalid numeric inputs

**Expected Behavior:**
Application should validate input and handle conversion errors gracefully with appropriate error messages.

**Actual Behavior:**
Invalid input causes unhandled exceptions instead of proper error handling.

---

## **Testing Instructions**

### **For Compilation Bugs (1, 5):**
1. Attempt to compile the project in IntelliJ
2. Read error messages about method signatures and variable scope
3. Fix return types and variable declarations

### **For Runtime Bugs (2, 4, 6, 8):**
1. Test with various inputs to trigger exceptions
2. Use debugger to trace where errors occur
3. Verify string conversions and array access

### **For Logic Bugs (3, 7):**
1. Test loop behavior with different scenarios
2. Trace counter values through execution
3. Verify loop conditions and updates

**Verification Scenarios:**
- **Single message**: "Hello world" with timestamp "1200"
- **Multiple messages**: 3-4 messages with different timestamps
- **Edge cases**: Empty messages, invalid timestamps, very long messages
- **Menu navigation**: Test all menu options and exit functionality

**Final Test:** Application should process messages correctly, display accurate statistics, and handle both valid and invalid inputs appropriately.