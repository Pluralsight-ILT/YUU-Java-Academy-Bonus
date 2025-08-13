# **Exercises – String Type Converter**

In these exercises you will create a String Type Converter application. You will practice converting strings to different primitive data types and converting other types back to strings, while handling different formatting scenarios.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 2**

Create a Java application named StringTypeConverter. This application will demonstrate how to convert strings to integers, doubles, and booleans, and how to convert other data types back to formatted strings.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Use Scanner to collect string inputs that represent different data types
- Convert string inputs to int using Integer.parseInt()
- Convert string inputs to double using Double.parseDouble()
- Convert string inputs to boolean using Boolean.parseBoolean()
- Perform calculations with the converted numeric values
- Convert numeric results back to strings using String.valueOf()
- Demonstrate string formatting with converted values
- Show before/after conversions and perform validation by displaying data types
- Use string concatenation to create a comprehensive conversion report

**Expected Output Example:**
```
=== String Type Converter ===

Enter a number as text: 47
Enter a decimal as text: 12.85
Enter true or false: true

=== Conversion Results ===
String "47" → Integer: 47
String "12.85" → Double: 12.85  
String "true" → Boolean: true

=== Calculations with Converted Values ===
Integer calculation: 47 + 10 = 57
Double calculation: 12.85 * 2 = 25.7
Boolean operation: true AND false = false

=== Converting Back to Strings ===
Result 57 as String: "57"
Result 25.7 as String: "25.7"
Final formatted message: "The sum is 57 and the product is 25.7"
```