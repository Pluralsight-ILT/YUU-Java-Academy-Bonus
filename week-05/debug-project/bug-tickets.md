# Password Manager - Bug Tickets

## **Bug #1: Access Modifier Issue**
**Priority:** High  
**Component:** PasswordManager Class

**Description:**
The password strength validation is not working correctly due to an access modifier problem. The validation logic appears to be implemented but cannot be accessed or used properly.

**Steps to Reproduce:**
1. Try to compile and run the application
2. Notice compilation errors or access issues related to password validation
3. Check how the validation method is being called

**Expected Behavior:**
Password validation should work correctly and be accessible from the appropriate locations.

**Actual Behavior:**
Access modifier prevents proper usage of the validation functionality.

---

## **Bug #2: Unit Test Failure**
**Priority:** Medium  
**Component:** PasswordManagerTest Class

**Description:**
The unit tests are failing even when the password generation appears to work correctly. The test logic seems to have an error in how it validates the generated passwords.

**Steps to Reproduce:**
1. Run the PasswordManagerTest class
2. Observe test failures
3. Compare expected vs actual test results

**Expected Behavior:**
Unit tests should pass when the password generation is working correctly.

**Actual Behavior:**
Tests fail due to incorrect test logic or assertions.

---

## **Bug #3: Static Method Problem**
**Priority:** Medium  
**Component:** Statistics Tracking

**Description:**
The password generation statistics are not being tracked correctly. The counter for total passwords generated is not updating properly, suggesting an issue with static method or variable usage.

**Steps to Reproduce:**
1. Generate multiple passwords
2. Check the statistics display
3. Notice that the count doesn't increase or resets incorrectly

**Expected Behavior:**
Static variables should maintain their values across multiple method calls and track cumulative statistics.

**Actual Behavior:**
Statistics counter is not working correctly due to improper static usage.

---

## **Testing Instructions**

### **For Bug #1 (Access Modifier):**
1. Check compilation errors and method visibility
2. Verify which methods should be public vs private
3. Test that external classes can access appropriate methods

### **For Bug #2 (Unit Test):**
1. Run the test class and examine failure messages
2. Check test assertions and expected values
3. Verify test logic matches the actual implementation

### **For Bug #3 (Static Methods):**
1. Test statistics tracking across multiple password generations
2. Verify static variables retain values between method calls
3. Check that static methods are called correctly

**Final Verification:** Application should generate passwords, validate them correctly, track statistics properly, and pass all unit tests.