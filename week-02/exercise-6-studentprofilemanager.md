# **Exercises: Student Profile Manager**

Build a `Student` class with fields and methods, instantiate at least three objects, and show that each one maintains its own independent state.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 6**

Create a Java application named `StudentProfileManager`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**`Student` class requirements:**
- Fields: `name` (String), `studentId` (int), `gpa` (double), `isEnrolled` (boolean)
- Constructor: accepts name and studentId, initializes gpa to 0.0 and isEnrolled to false
- Methods: `enroll()`, `withdraw()`, `updateGpa(double newGpa)`, `addCourse()`, `dropCourse()`
- Getter methods for all fields
- `getProfile()`: returns a formatted string with all student information

**In `main`:**
- Instantiate at least 3 Student objects
- Call methods to change each student's state
- Display profiles before and after changes
- Confirm that changes to one object do not affect the others
