# **Exercises – String Formatting Challenge**

In these exercises you will create a Personal Information Formatter application. You will practice string concatenation techniques and use Math class methods to format numerical data in different ways.

Remember to create your projects in the pluralsight/workbook-1 folder.

---

## **EXERCISE 2**

Create a Java application named PersonalInfoFormatter. This application will take personal information stored in variables and format it into multiple professional output strings using various concatenation techniques.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create variables for: firstName = "Sarah", lastName = "Johnson", age = 28, annualSalary = 67543.789
- Use Math class methods to format the salary in three different ways: rounded to nearest dollar, rounded to nearest thousand, and rounded to two decimal places
- Create at least 4 different formatted output strings using various string concatenation approaches
- Display a formal introduction, a brief summary, a financial overview, and a custom formatted message
- Demonstrate different concatenation techniques (+ operator, multiple operations, etc.)

**Expected Output Example:**
```
=== Personal Information Formatter ===

Formal Introduction: Mr. David Wilson, age 34

Brief Summary: David Wilson is 34 years old and works in our company.

Financial Overview: Annual salary of $72544.00 (rounded: $73000)

Custom Message: Hello! My name is David Wilson. 
I am 34 years old and my current salary is $72544.

=== Salary Formatting Examples ===
Original: $72544.234
Rounded to dollar: $72544
Rounded to thousand: $73000  
Two decimal places: $72544.23
```