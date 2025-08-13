# **Exercises – Mathematical Expression Builder**

In these exercises you will create a Mathematical Expression Builder application. You will demonstrate operator precedence and practice using variables to store intermediate calculations while building complex mathematical expressions step-by-step.

Remember to create your projects in the pluralsight/workbook-1 folder.

---

## **EXERCISE 1**

Create a Java application named MathExpressionBuilder. This application will calculate a complex mathematical expression by breaking it down into steps and demonstrating how operator precedence affects the results.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Use the values: `int x = 12`, `int y = 7`, `int z = 2`, `double w = 1.8`
- Calculate the expression `x + y * z - w` both directly and step-by-step using intermediate variables
- Show how adding parentheses `(x + y) * z - w` changes the result
- Use string concatenation to display all calculations with clear, descriptive output
- Demonstrate that operator precedence matters by comparing the two different results

**Expected Output Example:**
```
=== Mathematical Expression Builder ===
Original expression: 12 + 7 * 2 - 1.8
Step-by-step result: 24.2
Direct calculation: 24.2

With parentheses: (12 + 7) * 2 - 1.8 = 36.2
Difference: 12.0
```