# **Exercises: String Formatting Challenge**

Given a set of personal and financial variables, build several formatted output strings: a name display, a profile summary, and a salary breakdown showing three different levels of rounding.

Remember to create your projects in the pluralsight/workbook-1 folder.

---

## **EXERCISE 2**

Create a Java application named `PersonalInfoFormatter`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

Start with these variables. Do not change the values:

```java
String firstName = "Sarah";
String lastName = "Johnson";
int age = 28;
double annualSalary = 67543.789;
```

---

### Rounding with Math

Before you use them, understand what these methods actually do:

- `Math.round(value)`: rounds to the nearest whole number (67543.789 → 67544)
- `Math.floor(value / 1000) * 1000`: rounds down to the nearest thousand (67543.789 → 67000)
- `Math.round(value * 100) / 100.0`: shifts the decimal, rounds, then shifts back (67543.789 → 67543.79)

Each one has a use case. A rounded-to-dollar salary is good for a quick display. Rounded-to-thousand is useful for ranges or reports. Two decimal places is what you'd show on a formal document.

Compute all three and store them in variables before building your output strings.

---

### What to Build

Build at least four output strings using string concatenation. Your output should include:

- A **name display**: something that shows Sarah's full name and age in a readable line
- A **profile summary**: a sentence or two describing who she is, written like it might appear in an HR system or employee directory
- A **salary section**: show the original salary value alongside all three rounded versions, labeled clearly so a reader knows what they're looking at
- A **formatted message**: write it as if Sarah is introducing herself, pulling her name, age, and a salary figure together into one readable paragraph

Your output doesn't need to match any exact format, but it should be clean, labeled, and consistent.
