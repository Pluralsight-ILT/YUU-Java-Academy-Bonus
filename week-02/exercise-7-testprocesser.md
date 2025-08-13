# **Exercises – Test Score Processor**

In these exercises you will create a Test Score Processor application. You will practice using for loops with arrays to calculate statistics and process numerical data, while organizing your code using methods.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 7**

Create a Java application named TestScoreProcessor. This application will use arrays and for loops to collect and analyze test scores, calculating various statistics using methods to organize the code.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create an array to store 8 test scores (double values)
- Use Scanner and a for loop to collect the test scores from the user
- Create separate methods: calculateAverage(), findHighest(), findLowest(), countPassing()
- Use for loops within your methods to process the array data
- The countPassing() method should count scores 70.0 and above
- Create a displayStatistics() method that uses string concatenation to show all results
- In your main method, call all the methods and display a comprehensive report
- Use Math class methods for proper rounding in your calculations
- Demonstrate how arrays store multiple values and for loops process them efficiently

**Expected Output Example:**
```
=== Test Score Processor ===

Enter 8 test scores:
Score 1: 85.5
Score 2: 92.0
Score 3: 78.5
Score 4: 88.0
Score 5: 95.5
Score 6: 82.0
Score 7: 90.5
Score 8: 87.0

=== Statistical Analysis ===
Average Score: 87.4
Highest Score: 95.5
Lowest Score: 78.5
Passing Scores (70+): 8 out of 8
Pass Rate: 100.0%

=== Summary ===
Excellent class performance! All students passed with a strong average.
```