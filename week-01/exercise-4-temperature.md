# **Exercises: Temperature Converter with Precision**

Convert a set of temperatures between Celsius, Fahrenheit, and Kelvin. Display each result at three precision levels, then extend the program to accept user input.

Remember to create your projects in the pluralsight/workbook-1 folder.

---

## **EXERCISE 4**

Create a Java application named `TemperatureConverter`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

### **PART 1**

Before you write any conversion code, there's a gotcha you need to know about: **integer division**.

In Java, when you divide two whole numbers, Java returns a whole number. It drops the decimal entirely. So this:

```java
int result = 9 / 5;  // result is 1, not 1.8
```

That's a problem when your formula is `(C * 9/5) + 32`. If Java treats `9/5` as integer division, you'll get `1` instead of `1.8`, and your conversion will be wrong.

The fix is simple: use a decimal point to tell Java you want floating-point math:

```java
double fahrenheit = (celsius * 9.0 / 5) + 32;
```

Keep this in mind as you write your formulas.

**Requirements:**
- Create `double` variables for your starting temperatures: `celsius = 23.7`, `fahrenheit = 98.6`, `kelvin = 300.15`
- Convert celsius to fahrenheit: `F = (C * 9.0/5) + 32`
- Convert fahrenheit to celsius: `C = (F - 32) * 5.0/9`
- Convert celsius to kelvin: `K = C + 273.15`
- Convert kelvin to celsius: `C = K - 273.15`
- Display each conversion result at three precision levels: rounded to a whole number using `Math.round()`, one decimal place, and two decimal places
- Use string concatenation to build your output

**Expected Output Example:**
```
=== Temperature Converter ===

Starting Temperature: 23.7°C

Conversion Results:
23.7°C = 75°F (rounded)
23.7°C = 74.7°F (one decimal)
23.7°C = 74.66°F (two decimals)

23.7°C = 297°K (rounded)
23.7°C = 296.9°K (one decimal)
23.7°C = 296.85°K (two decimals)

=== Reverse Conversions ===
98.6°F = 37°C (rounded)
300.15°K = 27°C (rounded)
```

### **PART 2**

Now make it interactive. Instead of hardcoded values, ask the user to enter a temperature in Celsius and convert it for them.

Use the `Scanner` class to read input from the user. Your program should:
- Prompt the user to enter a temperature in Celsius
- Read their input
- Convert it to Fahrenheit and Kelvin
- Display the results (rounded is fine for this part)

**Example interaction:**
```
Enter a temperature in Celsius: 100
100.0°C = 212°F
100.0°C = 373°K
```

The logic is the same as Part 1. You're just replacing the hardcoded starting value with whatever the user types in.
