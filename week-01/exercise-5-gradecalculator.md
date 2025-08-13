# **Exercises – Grade Calculator with Categories**

In these exercises you will create a Grade Calculator application. You will use Scanner to collect multiple test scores, calculate weighted averages using different grade categories, and use conditional statements to determine letter grades with appropriate messages.

Remember to create your projects in the pluralsight/workbook-1 folder.

---

## **EXERCISE 5**

Create a Java application named GradeCalculator. This application will collect student test scores in different categories, calculate a weighted final grade, and determine the letter grade with personalized feedback messages.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Use Scanner to prompt for student name and scores in three categories: Tests (40%), Quizzes (30%), Homework (30%)
- Collect 2 test scores, 3 quiz scores, and 4 homework scores
- Calculate the average for each category and apply the weighted percentages
- Use conditional statements to assign letter grades: A (90+), B (80-89), C (70-79), D (60-69), F (below 60)
- Display a comprehensive grade report with category averages, weighted final grade, letter grade, and personalized message
- Use Math class methods for proper rounding and string concatenation for formatted output

**Expected Output Example:**
```
=== Grade Calculator ===
Enter student name: Maria Rodriguez

[After collecting all scores...]

=== Grade Report for Maria Rodriguez ===

Category Averages:
Tests (40%): 88.5
Quizzes (30%): 92.3  
Homework (30%): 95.8

Weighted Final Grade: 91.4
Letter Grade: A

Congratulations Maria! Your excellent performance across all categories 
has earned you an A in the course. Keep up the outstanding work!
```