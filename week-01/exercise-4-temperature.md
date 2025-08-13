# **Exercises – Temperature Converter with Precision**

In these exercises you will create a Temperature Converter application. You will practice arithmetic operators for temperature conversion formulas and use Math class methods to control precision in your results while building formatted output strings.

Remember to create your projects in the pluralsight/workbook-1 folder.

---

## **EXERCISE 4**

Create a Java application named TemperatureConverter. This application will convert temperatures between Celsius, Fahrenheit, and Kelvin scales while demonstrating different precision levels using Math class rounding methods.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

### **PART 1**

**Requirements:**
- Create variables for starting temperatures: celsius = 23.7, fahrenheit = 98.6, kelvin = 300.15
- Convert celsius to fahrenheit using the formula: F = (C * 9/5) + 32
- Convert fahrenheit to celsius using the formula: C = (F - 32) * 5/9
- Convert celsius to kelvin using the formula: K = C + 273.15
- Convert kelvin to celsius using the formula: C = K - 273.15
- Display results with different precision levels using Math.round(), one decimal place, and two decimal places
- Use string concatenation to create a professional temperature conversion report
- Calculate and display the temperature differences between scales

**Expected Output Example:**
```
=== Temperature Converter ===

Starting Temperature: 18.3°C

Conversion Results:
18.3°C = 65°F (rounded)
18.3°C = 64.9°F (one decimal)  
18.3°C = 64.94°F (two decimals)

18.3°C = 291°K (rounded)
18.3°C = 291.5°K (one decimal)
18.3°C = 291.45°K (two decimals)

=== Reverse Conversions ===
75.2°F = 24°C (rounded)
285.9°K = 13°C (rounded)

Temperature Span: 62.2°F range equals 34.6°C range
```


### **PART 2**

**Requirements:**
- Update your application to take in a users input
- Allow a user to convert a value they enter by using the Scanner class