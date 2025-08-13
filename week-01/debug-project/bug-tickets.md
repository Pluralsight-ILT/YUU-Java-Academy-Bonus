# Pizza Order System - Bug Tickets

## **Operator and Math Bugs**

### **Ticket #1: Operator Precedence Error in Price Calculation**
**Priority:** High  
**Component:** Price Calculation Logic

**Description:**
The subtotal calculation is not following proper operator precedence. When calculating the total cost of pizza plus toppings, the mathematical operations are not being performed in the correct order, resulting in incorrect pricing.

**Steps to Reproduce:**
1. Run the application
2. Select any pizza size
3. Add toppings (try 3 toppings)
4. Notice the subtotal is incorrect

**Expected Behavior:**
Subtotal should be: pizza base price + (number of toppings × $1.50)

**Actual Behavior:**
The calculation order is wrong, producing an incorrect subtotal.

---

### **Ticket #2: String Concatenation Display Issue**
**Priority:** Medium  
**Component:** Order Summary Display

**Description:**
The order summary is not displaying properly formatted strings. The concatenation of text and numbers in the receipt is malformed, making the output difficult to read.

**Steps to Reproduce:**
1. Complete a full order
2. Observe the order summary format
3. Notice formatting issues in the receipt display

**Expected Behavior:**
Clean, properly formatted receipt with aligned text and currency values.

**Actual Behavior:**
String concatenation errors result in poorly formatted or incorrect display.

---

### **Ticket #3: Math.round() Method Usage Error**
**Priority:** High  
**Component:** Tax and Total Calculation

**Description:**
The final total amount is not being rounded properly to two decimal places. The Math.round() method is either missing or being used incorrectly, causing currency amounts to display with too many decimal places.

**Steps to Reproduce:**
1. Complete an order that results in tax calculation
2. Observe the final total amount
3. Notice incorrect decimal precision

**Expected Behavior:**
All currency amounts should display with exactly 2 decimal places (e.g., $17.04).

**Actual Behavior:**
Currency amounts show more than 2 decimal places or are not rounded correctly.

---

## **Input and Logic Bugs**

### **Ticket #4: Scanner Input Type Mismatch**
**Priority:** Critical  
**Component:** User Input Handling

**Description:**
The application crashes or behaves unexpectedly when collecting user input. There appears to be a mismatch between the Scanner method being used and the expected input type.

**Steps to Reproduce:**
1. Start the application
2. Try to enter information when prompted
3. Notice the application fails or skips input

**Expected Behavior:**
Scanner should properly collect string, integer, and other input types as needed.

**Actual Behavior:**
Input collection fails due to incorrect Scanner method usage.

---

### **Ticket #5: Conditional Logic Error for Student Discount**
**Priority:** High  
**Component:** Discount Calculation

**Description:**
The student discount (10% for ages 17 and under) is not being applied correctly. The conditional statement logic for determining discount eligibility has an error.

**Steps to Reproduce:**
1. Enter age 16 (should get student discount)
2. Complete the order
3. Notice the discount is not applied or applied incorrectly

**Expected Behavior:**
- Ages 17 and under: 10% student discount
- Ages 65 and over: 15% senior discount  
- Ages 18-64: No discount

**Actual Behavior:**
Student discount logic is incorrect or not working.

---

### **Ticket #6: Variable Scope Problem in calculateTax() Method**
**Priority:** Medium  
**Component:** Method Structure

**Description:**
There is a variable scope error in the calculateTax() method (or equivalent tax calculation section). Variables are being declared or used in the wrong scope, causing compilation errors or incorrect calculations.

**Steps to Reproduce:**
1. Try to compile the project
2. Notice compilation errors related to variable scope
3. Or run the application and see tax calculation fails

**Expected Behavior:**
Variables should be properly scoped and accessible where needed for tax calculations.

**Actual Behavior:**
Variable scope error prevents proper tax calculation or compilation.

---

### **Ticket #7: Incorrect Topping Count Logic**
**Priority:** Medium  
**Component:** Topping Calculation

**Description:**
The logic for counting and calculating topping costs has an error. Either the count is wrong, the multiplication is incorrect, or there's an off-by-one error in the calculation.

**Steps to Reproduce:**
1. Order a pizza with 2 toppings
2. Check the topping line in the receipt
3. Verify: should show 2 toppings × $1.50 = $3.00

**Expected Behavior:**
Topping count and cost calculation should be accurate.

**Actual Behavior:**
Topping count or cost calculation is incorrect.

---

### **Ticket #8: Age Validation Logic Error**
**Priority:** Low  
**Component:** Input Validation

**Description:**
The age validation logic has an error that either accepts invalid ages (negative numbers, unrealistic values) or incorrectly categorizes valid ages for discount purposes.

**Steps to Reproduce:**
1. Try entering edge case ages (0, negative numbers, very high numbers)
2. Test boundary conditions (exactly 17, exactly 18, exactly 65)
3. Notice incorrect behavior

**Expected Behavior:**
- Accept reasonable ages (1-120)
- Correctly categorize for discounts at boundaries
- Handle invalid input gracefully

**Actual Behavior:**
Age validation or categorization logic is incorrect.

---

## **Testing Instructions**

### **For Compilation Bugs (4, 6):**
1. Attempt to compile the project in IntelliJ
2. Read error messages carefully
3. Fix syntax and scope errors first

### **For Calculation Bugs (1, 3, 7):**
1. Test with known values to verify math
2. Use a calculator to check expected results
3. Trace through the calculation step by step

### **For Logic Bugs (5, 8):**
1. Test boundary conditions (ages 17, 18, 65)
2. Test normal cases (student, adult, senior)
3. Verify discount percentages match requirements

### **For Display Bugs (2):**
1. Compare output to the expected sample
2. Check string concatenation syntax
3. Ensure proper formatting of currency values

**Verification:** After fixing each bug, test with multiple scenarios:
- Small pizza, no toppings, age 25 (adult, no discount)
- Large pizza, 4 toppings, age 16 (student discount)
- Medium pizza, 2 toppings, age 70 (senior discount)

**Final Test:** Your output should match the expected sample format with accurate calculations for all test cases.