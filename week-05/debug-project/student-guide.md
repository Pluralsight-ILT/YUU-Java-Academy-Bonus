# Student Guide - Password Manager Debug Project

## Progress Tracker

### Bug Fixes (Complete All)
- [ ] **Bug #1** - Access Modifier Issue
- [ ] **Bug #2** - Unit Test Failure  
- [ ] **Bug #3** - Static Method Problem

---

## Project Overview

The Password Manager is a small Java application demonstrating access modifiers, unit testing, and static methods. The system generates secure passwords, validates them, and tracks usage statistics.

### Key Features
- **Password Generation**: Create random secure passwords
- **Validation System**: Check password strength requirements
- **Usage Tracking**: Monitor password generation statistics using static variables
- **Encapsulation**: Protect sensitive data with proper access modifiers

### Current State
The application has **3 bugs** that prevent it from working correctly. Each bug demonstrates a different Week 5 concept that needs to be fixed.

---

## Getting Started

### Project Structure
Your project contains:
- **PasswordManager.java** - Main class with password operations
- **PasswordManagerTest.java** - Unit tests that are currently failing

### Expected Functionality
When working correctly, the application should:
1. Generate secure passwords with specified criteria
2. Validate password strength properly
3. Track statistics across multiple password generations
4. Pass all unit tests

---

## 🔧 IntelliJ IDEA Setup

### Opening the Project
1. **Create New Project** in IntelliJ IDEA
2. **Copy both Java files** to your src folder
3. **Run the tests** to see current failures
4. **Run the main application** to observe incorrect behavior

---

## Understanding the Bugs

### Bug Categories
1. **Access Modifier Bug** - Incorrect visibility causing compilation or access issues
2. **Unit Test Bug** - Test logic error causing false results
3. **Static Method Bug** - Improper use of static members causing incorrect behavior

### Debugging Strategy
1. **Run the tests first** - See which tests are failing and why
2. **Check compilation errors** - Fix any access modifier issues
3. **Run the main application** - Observe incorrect behavior in password generation or statistics
4. **Fix one bug at a time** - Test after each fix to verify the solution

---

## Expected Working Output

```
=== Password Manager ===

Generated password: Kj9$mN2pQ!
Password strength: STRONG
Passwords generated today: 1

Generated password: 3xP@8vCzW#
Password strength: STRONG  
Passwords generated today: 2

Statistics:
- Total passwords generated: 2
- Average length: 10.0
- Strong passwords: 2

Running Tests...
✓ All tests passed!
```

---

## Completion Checklist

Before submitting your work, ensure:
- [ ] All compilation errors are resolved
- [ ] Unit tests pass completely
- [ ] Password generation works correctly
- [ ] Statistics tracking functions properly
- [ ] Access modifiers are used appropriately

---

## Getting Help

If you encounter issues:
1. **Read test failure messages carefully** - they often indicate the exact problem
2. **Check access modifier usage** - ensure fields and methods have appropriate visibility
3. **Verify static vs instance usage** - make sure static methods and variables are used correctly
4. **Test incrementally** - fix one bug at a time and verify the fix works

Remember: This is a small project focused on reinforcing Week 5 concepts. Each bug teaches an important lesson about access control, testing, or static members!