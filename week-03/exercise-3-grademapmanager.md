# **Exercises – Grade Book Map**

In these exercises you will create a Grade Book Map application. You will practice using HashMap to store key-value pairs and organize your code with methods for managing student grades.

Remember to create your projects in the pluralsight/workbook-3 folder.

---

## **EXERCISE 3**

Create a Java application named GradeBookMap. This application will use HashMap to manage student names and their grades with organized methods.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a HashMap<String, Double> to store student names and grades
- Create methods: addGrade(), updateGrade(), removeStudent(), getGrade(), displayAllGrades()
- Use Scanner to collect student information and implement a menu system
- Use HashMap methods: put(), get(), remove(), containsKey(), keySet()
- Calculate and display class average using the map values
- Handle cases where student names don't exist in the map
- Show how maps maintain key-value relationships
- Display grades in a formatted table

**Expected Output Example:**
```
=== Grade Book Map ===

Current Grades:
Alice: 92.5
Bob: 87.0  
Charlie: 95.5

Menu Options:
1. Add/Update grade
2. Remove student
3. Get specific grade
4. Show class average

Enter choice: 4

Class Average: 91.7
Total students: 3
Highest grade: Charlie (95.5)
```