# **Exercises – String Converter Challenge**

In these exercises you will create a String Converter application. You will practice various string manipulation methods to transform text in different ways and demonstrate how string methods can be chained together for complex transformations.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 1**

Create a Java application named StringConverter. This application will take a sample text string and demonstrate various string conversion methods, showing before and after results for each transformation.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Use Scanner to prompt the user to enter any text string they want to transform
- Demonstrate these string methods: trim(), toUpperCase(), toLowerCase(), replace(), substring()
- Show the original string length vs trimmed string length
- Extract specific portions of the string using substring() with different parameters
- Replace words and characters using replace() method (let user choose what to replace)
- Chain multiple string methods together to create complex transformations
- Use string concatenation to display clear before/after comparisons for each operation

**Expected Output Example:**
```
=== String Converter Challenge ===

Original: "  Welcome to Advanced Java Programming!  "
Length: 37

=== Individual Transformations ===
Trimmed: "Welcome to Advanced Java Programming!"
Uppercase: "WELCOME TO ADVANCED JAVA PROGRAMMING!"
Lowercase: "welcome to advanced java programming!"
Replace 'Java' with 'Python': "Welcome to Advanced Python Programming!"
Substring (11-19): "Advanced"

=== Complex Transformations ===
Trimmed + Uppercase: "WELCOME TO ADVANCED JAVA PROGRAMMING!"
Replace + Lowercase: "welcome to advanced python programming!"
Substring + Uppercase: "ADVANCED"

Final length after trim: 35
Characters removed: 2
```