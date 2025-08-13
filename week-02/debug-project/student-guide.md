# Student Guide - Text Message Analytics Tool

## Progress Tracker

### Phase 1: Bug Fixes (Complete First)
- [ ] **Ticket #1** - Method Return Type Mismatch Error
- [ ] **Ticket #2** - String to Integer Conversion Error
- [ ] **Ticket #3** - While Loop Infinite Loop Issue
- [ ] **Ticket #4** - For Loop Array Bounds Error
- [ ] **Ticket #5** - Method Variable Scope Problem
- [ ] **Ticket #6** - String Method Chain Error
- [ ] **Ticket #7** - Loop Counter Logic Error
- [ ] **Ticket #8** - Missing Type Conversion Validation

---

## Project Overview

The Text Message Analytics Tool is a Java console application that demonstrates methods, string type conversion, and loop structures. The system analyzes text messages to provide statistics like word count, character analysis, sentiment scoring, and message formatting.

### Key Features
- **Message Input**: Collect multiple text messages from users
- **Text Analysis**: Count words, characters, and analyze content
- **String Conversion**: Parse timestamps, convert numeric data
- **Statistics Generation**: Calculate averages, totals, and percentages
- **Formatted Reports**: Display analytics in organized tables
- **Menu System**: Navigate through different analysis options

### Current Functionality
The application should:
1. Welcome user and display analytics menu
2. Allow input of multiple text messages
3. Process each message for word count, character count, timestamp
4. Convert string timestamps to numeric values for sorting
5. Analyze message sentiment (positive/negative word detection)
6. Calculate statistics across all messages
7. Display formatted analytics report

---

## Getting Started

### 1. Project Structure
Your project contains:
- **TextMessageAnalytics.java** - Main application class with bugs to fix
- **Sample Input/Output** - Expected behavior examples

### 2. Understanding the Current State
The application currently has:
- **No 8 bugs** preventing proper operation
- **No Method signature errors** that cause compilation issues
- **No Loop logic errors** causing incorrect processing
- **No String conversion errors** leading to runtime exceptions

---

## IntelliJ IDEA Setup

### Opening the Project
1. **Create New Project** in IntelliJ IDEA
2. **Copy the TextMessageAnalytics.java** file to your src folder
3. **File → Open** and select your project folder
4. Let IntelliJ detect and configure the project

### Project Structure
- Source code will be in your default package or `src` folder
- The main class contains all functionality organized in methods
- No external dependencies required

---

## Running the Application

### Setting Up Run Configuration
1. **Right-click** on `TextMessageAnalytics.java` in the project tree
2. Select **"Run 'TextMessageAnalytics.main()'"**
3. IntelliJ will create a run configuration automatically

### Expected Sample Run
```
=== Text Message Analytics Tool ===
Welcome to Message Analyzer Pro!

Menu:
1. Add Messages
2. Analyze Messages
3. View Statistics
4. Exit

Enter choice: 1
How many messages to add? 3

Message 1: Hey there! How are you doing today?
Timestamp (HHMM): 1430

Message 2: I'm doing great, thanks for asking!
Timestamp (HHMM): 1445

Message 3: Awesome! Let's meet up later.
Timestamp (HHMM): 1500

=== Message Analytics Report ===
Total Messages: 3
Average Words per Message: 6.7
Total Characters: 94
Positive Messages: 2
Most Active Hour: 14 (2:00 PM)

Message Details:
[14:30] "Hey there! How are you doing today?" - 8 words, 32 chars
[14:45] "I'm doing great, thanks for asking!" - 7 words, 30 chars  
[15:00] "Awesome! Let's meet up later." - 5 words, 24 chars
```

---

## Understanding the Bugs

### Categories of Issues
1. **Method Errors** - Wrong return types, parameter mismatches
2. **String Conversion** - Incorrect parsing methods, missing validation
3. **Loop Logic** - Infinite loops, incorrect bounds, counter errors
4. **Variable Scope** - Variables declared in wrong methods or scope
5. **String Operations** - Incorrect method chaining, case sensitivity

### Debugging Strategy
1. **Fix Compilation Errors First** - Focus on method signatures and syntax
2. **Test Loop Behavior** - Ensure loops terminate and process correctly
3. **Verify String Conversions** - Check parsing of timestamps and numeric data
4. **Test Method Calls** - Ensure methods return correct values and types
5. **Validate Input Processing** - Test with various message inputs

---

## Key Concepts Practiced

### Week 2 Topics Covered
- **Methods**: Return types, parameters, variable scope, organization
- **String Type Conversion**: parseInt(), parseDouble(), validation
- **While Loops**: Menu systems, input validation, user interaction
- **For Loops**: Array/collection processing, statistics calculation
- **String Operations**: length(), substring(), toLowerCase(), split()

### Common Patterns
- Method organization for code reusability
- String parsing and validation
- Loop-based data processing
- Accumulator patterns for statistics
- Formatted string output generation

---

## Development Workflow

### Recommended Approach
1. **Start with Compilation** 
   - Fix method signature errors and syntax issues
   - Ensure all methods have correct return types and parameters

2. **Test Menu System**
   - Verify while loop works correctly for menu navigation
   - Check that loop termination conditions work

3. **Focus on String Processing**
   - Test string to integer conversions for timestamps
   - Verify string analysis methods work correctly

4. **Debug Loop Logic**
   - Ensure for loops process all messages correctly
   - Check array bounds and counter logic

5. **Verify Method Integration**
   - Test that methods call each other correctly
   - Ensure data flows properly between methods

### Testing Different Scenarios
- **Single message** - basic functionality
- **Multiple messages** - loop processing
- **Edge cases** - empty messages, invalid timestamps
- **Different message lengths** - various word counts

---

## Expected Learning Outcomes

After completing this debug project, you should understand:
- How to design and implement methods with proper signatures
- String type conversion techniques and error handling
- While loop implementation for user interfaces
- For loop usage for data processing and analysis
- Variable scope and method organization principles
- String manipulation methods and their applications
- Error handling for user input validation

---

## Completion Checklist

Before submitting your work, ensure:
- [ ] Code compiles without errors
- [ ] Application runs and displays the main menu
- [ ] Menu navigation works correctly (while loop)
- [ ] Messages can be added and stored properly
- [ ] Timestamp conversion from string to integer works
- [ ] Word count and character count calculations are accurate
- [ ] Statistics calculations work correctly
- [ ] All methods return appropriate values
- [ ] String analysis methods work properly
- [ ] Application handles multiple messages correctly

---

## Getting Help

If you encounter issues:
1. **Check method signatures** - ensure return types and parameters match usage
2. **Test string conversions** - verify parsing methods are correct
3. **Debug loop conditions** - ensure loops terminate properly
4. **Trace method calls** - verify data flows correctly between methods
5. **Check variable scope** - ensure variables are accessible where needed
6. **Test with simple inputs** - start with basic cases before complex scenarios

### Debugging Steps
1. **Read error messages carefully** - they often indicate exact problems
2. **Check method calls** - ensure parameters and return types match
3. **Test loops in isolation** - verify each loop works independently
4. **Use print statements** - debug variable values at different points
5. **Test string operations** - verify parsing and manipulation methods

Remember: This project reinforces method design, string processing, and loop implementation. Use it to strengthen your understanding of organizing code with methods and processing data with loops!