# **Exercises: Grade Calculator with Categories**

Collect a student's scores across three weighted categories, calculate their final grade, and print a report with the letter grade. Plan before you code.

Remember to create your projects in the pluralsight/workbook-1 folder.

---

## **EXERCISE 5**

Create a Java application named `GradeCalculator` that collects a student's scores across three categories, calculates a weighted final grade, and displays a grade report.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

Weighted averages are used everywhere: GPAs, performance reviews, fitness apps, financial scoring. The idea is that not all inputs carry equal importance. In this case, tests matter more than quizzes, which matter more than homework. To apply a weight, multiply a category's average by its percentage as a decimal, then add the results together:

```
weightedGrade = (testAvg * 0.40) + (quizAvg * 0.30) + (homeworkAvg * 0.30)
```

**Requirements:**
- Use Scanner to prompt for student name and scores in three categories: Tests (40%), Quizzes (30%), Homework (30%)
- Collect 2 test scores, 3 quiz scores, and 4 homework scores
- Calculate the average for each category and apply the weighted percentages
- Use conditional statements to assign letter grades: A (90+), B (80-89), C (70-79), D (60-69), F (below 60)
- Display a grade report showing category averages, the weighted final grade, and the letter grade with a short status message appropriate to the result
- Use Math class methods for rounding and string concatenation for formatted output

Before you write any code, sketch out two things: the order of your Scanner prompts (you are collecting 9 inputs total), and a simple decision table for the grade boundaries. Writing those out first will make the code much easier to get right.

Use your own name, or someone you know, as test data. It makes spotting output errors a lot faster than staring at placeholder values.

**Expected Output Example:**
```
=== Grade Calculator ===
Enter student name: Maria Rodriguez

Enter test score 1: 87
Enter test score 2: 90
Enter quiz score 1: 94
Enter quiz score 2: 91
Enter quiz score 3: 92
Enter homework score 1: 98
Enter homework score 2: 95
Enter homework score 3: 97
Enter homework score 4: 94

=== Grade Report for Maria Rodriguez ===

Category Averages:
  Tests (40%):     88.5
  Quizzes (30%):   92.3
  Homework (30%):  96.0

Weighted Final Grade: 91.9
Letter Grade: A

Maria Rodriguez is passing with an A.
```
