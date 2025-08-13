# Student Guide - Pizza Order System

## Progress Tracker

### Phase 1: Bug Fixes (Complete First)
- [ ] **Ticket #1** - Operator Precedence Error in Price Calculation
- [ ] **Ticket #2** - String Concatenation Display Issue
- [ ] **Ticket #3** - Math.round() Method Usage Error
- [ ] **Ticket #4** - Scanner Input Type Mismatch
- [ ] **Ticket #5** - Conditional Logic Error for Student Discount
- [ ] **Ticket #6** - Variable Scope Problem in calculateTax() Method
- [ ] **Ticket #7** - Incorrect Topping Count Logic
- [ ] **Ticket #8** - Age Validation Logic Error

---

## Project Overview

The Pizza Order System is a Java console application that demonstrates fundamental programming concepts including operators, string manipulation, Math class usage, Scanner input, and conditional statements. The system calculates pizza costs with size options, toppings, discounts, and tax.

### Key Features
- **Size Selection**: Small ($8.99), Medium ($12.99), Large ($16.99)
- **Topping Management**: Additional toppings at $1.50 each
- **Discount System**: Student (10%) and Senior (15%) discounts based on age
- **Tax Calculation**: 8.25% sales tax on final amount
- **Order Summary**: Formatted receipt with itemized costs

### Current Functionality
The application should:
1. Welcome the customer and collect their name
2. Display pizza size options with prices
3. Allow selection of pizza size
4. Collect number of additional toppings
5. Ask for customer age for discount eligibility
6. Calculate subtotal, apply discounts, add tax, and display total
7. Show a formatted order summary

---

## Getting Started

### 1. Project Structure
Your project contains:
- **PizzaOrderSystem.java** - Main application class with bugs to fix
- **Sample Input/Output** - Expected behavior examples

### 2. Understanding the Current State
The application currently has:
- **No 8 bugs** preventing proper operation
- **No Compilation errors** that must be fixed first
- **No Logic errors** causing incorrect calculations

---

## IntelliJ IDEA Setup

### Opening the Project
1. **Create New Project** in IntelliJ IDEA
2. **Copy the PizzaOrderSystem.java** file to your src folder
3. **File → Open** and select your project folder
4. Let IntelliJ detect and configure the project

### Project Structure
- Source code will be in your default package or `src` folder
- The main class contains all functionality in one file
- No external dependencies required

---

## Running the Application

### Setting Up Run Configuration
1. **Right-click** on `PizzaOrderSystem.java` in the project tree
2. Select **"Run 'PizzaOrderSystem.main()'"**
3. IntelliJ will create a run configuration automatically

### Expected Sample Run
```
=== Welcome to Tony's Pizza Palace ===
Enter your name: Sarah

Pizza Sizes:
1. Small Pizza - $8.99
2. Medium Pizza - $12.99  
3. Large Pizza - $16.99

Enter your choice (1-3): 2
How many additional toppings? $1.50 each: 3
Enter your age: 19

=== Order Summary for Sarah ===
Medium Pizza: $12.99
Additional Toppings (3): $4.50
Subtotal: $17.49
Student Discount (10%): -$1.75
Tax (8.25%): $1.30
Total: $17.04

Thank you for your order!
```

---

## Understanding the Bugs

### Categories of Issues
1. **Compilation Errors** - Code that won't compile
2. **Operator Precedence** - Mathematical calculation errors
3. **String Formatting** - Display and concatenation problems
4. **Method Usage** - Incorrect Math class method calls
5. **Logic Errors** - Conditional statements and variable scope

### Debugging Strategy
1. **Fix Compilation Errors First** - Get the code to run
2. **Test Each Feature** - Run the application and test different scenarios
3. **Compare Expected vs Actual** - Use the sample output as reference
4. **Trace Through Logic** - Follow the calculation steps manually

---

## Key Concepts Practiced

### Week 1 Topics Covered
- **Operators**: Arithmetic operations and precedence
- **String Concatenation**: Building formatted output strings
- **Math Class**: Using Math.round() and other methods
- **Scanner**: Collecting different input types
- **Conditional Statements**: If/else logic for discounts and validation
- **Methods**: Basic method structure and variable scope

### Common Patterns
- Price calculations with proper operator precedence
- Formatted string output using concatenation
- Input validation and type handling
- Percentage calculations for discounts and tax

---

## Development Workflow

### Recommended Approach
1. **Start with Compilation** 
   - Fix syntax errors that prevent the code from running
   - Look for missing semicolons, parentheses, or variable declarations

2. **Test Basic Flow**
   - Run the application and follow the menu system
   - Enter different inputs to see where calculations break

3. **Focus on Calculations**
   - Check math operations for correct precedence
   - Verify discount and tax calculations
   - Ensure proper rounding

4. **Verify Output Formatting**
   - Compare your output to the expected sample
   - Fix string concatenation and display issues

### Testing Different Scenarios
- **Small pizza, no toppings, adult customer** (no discount)
- **Large pizza, many toppings, student** (10% discount)
- **Medium pizza, few toppings, senior** (15% discount)
- **Invalid inputs** (wrong menu choice, negative age)

---

## Debugging Tips

### IntelliJ Features to Use
- **Error Highlighting**: Red underlines show compilation errors
- **Quick Fixes**: Alt + Enter on errors for suggestions
- **Breakpoints**: Click line gutter to pause execution
- **Variable Inspection**: Hover over variables to see values
- **Step Debugging**: F8 to step through code line by line

### Common Issues to Look For
- **Missing operators** or incorrect operator precedence
- **Variable scope** - variables declared in wrong place
- **Method calls** - wrong Math class methods or parameters
- **Type mismatches** - Scanner reading wrong data type
- **Logic errors** - incorrect if/else conditions

---

## Expected Learning Outcomes

After completing this debug project, you should understand:
- How operator precedence affects mathematical calculations
- Proper string concatenation techniques for formatted output
- Correct usage of Math class methods like Math.round()
- Scanner input handling for different data types
- Conditional logic for business rules (discounts, validation)
- Variable scope and method organization
- Debugging techniques using IntelliJ IDEA

---

## Completion Checklist

Before submitting your work, ensure:
- [ ] Code compiles without errors
- [ ] Application runs and displays the welcome message
- [ ] All pizza sizes can be selected correctly
- [ ] Topping calculations work properly
- [ ] Student discount (17 and under) applies correctly
- [ ] Senior discount (65 and over) applies correctly
- [ ] Tax calculation is accurate
- [ ] Order summary displays properly formatted
- [ ] Edge cases work (no toppings, maximum toppings, etc.)

---

## Getting Help

If you encounter issues:
1. **Check IntelliJ's error indicators** and read error messages carefully
2. **Compare your output** to the expected sample output
3. **Test one feature at a time** to isolate problems
4. **Use println statements** to debug variable values
5. **Trace through calculations manually** to verify logic
6. **Review Week 1 concepts** - operators, strings, Math class, conditionals

### Debugging Steps
1. **Read the error message** - it often tells you exactly what's wrong
2. **Check line numbers** - IntelliJ shows where errors occur
3. **Verify variable names** - ensure spelling and case match
4. **Trace execution** - follow the program flow step by step
5. **Test incrementally** - fix one bug at a time and retest

Remember: This project reinforces fundamental Java concepts through practical debugging. Use it as an opportunity to solidify your understanding of operators, strings, conditionals, and basic program structure!