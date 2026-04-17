# **Exercises: Test Score Processor**

Collect 8 test scores from the user, store them in an array, and use methods with for loops to calculate and display statistics.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 7**

Create a Java application named `TestScoreProcessor`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Declare a `double` array of size 8
- Use Scanner and a for loop to collect scores from the user
- Create and call these methods, each using a for loop to process the array:
  - `calculateAverage()`: returns the average score
  - `findHighest()`: returns the highest score
  - `findLowest()`: returns the lowest score
  - `countPassing()`: returns the count of scores at or above 70.0
- Create a `displayStatistics()` method that calls the others and prints the report
- Use `Math` class methods for rounding where appropriate
