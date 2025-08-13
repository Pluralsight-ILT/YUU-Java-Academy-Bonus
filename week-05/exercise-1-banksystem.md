# **Exercises – Access Modifiers and Encapsulation**

In these exercises you will create a Bank Account System that demonstrates proper encapsulation using private and public access modifiers. You will practice controlling access to class data and providing safe methods for interaction.

Remember to create your projects in the pluralsight/workbook-4 folder.

---

## **EXERCISE 1**

Create a Java application that models a bank account system with proper encapsulation. Design classes that protect sensitive data while providing controlled access through public methods.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a `BankAccount` class with private fields: accountNumber, accountHolder, balance, isActive
- Implement public getter methods for safe data access
- Create public methods: deposit(), withdraw(), getAccountInfo()
- Include private helper methods: validateAmount(), updateBalance()
- Create an `AccountManager` class to handle multiple accounts using an ArrayList
    - Store multiple BankAccount objects in a collection
    - Provide methods: createAccount(), findAccount(), displayAllAccounts()
    - Demonstrate that AccountManager can access BankAccount's public methods but not private fields
- Show compilation errors when trying to access private fields directly
- Include validation logic that prevents invalid operations (negative deposits, overdrafts)

**Expected Output Example:**
```
=== Bank Account System ===

Creating account for Sarah Johnson...
Account created successfully: ACC001

Attempting direct balance access: [Compilation Error - Cannot access private field]

Deposit $500.00: Success
Current balance: $500.00

Withdraw $200.00: Success  
Current balance: $300.00

Withdraw $400.00: Failed - Insufficient funds
Current balance: $300.00

Account Info: ACC001 - Sarah Johnson ($300.00) [Active]
```