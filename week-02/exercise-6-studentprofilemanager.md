# **Exercises – Student Profile Manager**

In these exercises you will create a Student Profile Manager application. You will practice creating a Student class with multiple fields and methods, then instantiate several Student objects to demonstrate how each object maintains its own data independently.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 6**

Create a Java application named StudentProfileManager. This application will demonstrate object instantiation by creating a Student class and working with multiple Student objects to manage student information.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a Student class with fields: name (String), studentId (int), gpa (double), isEnrolled (boolean)
- Include methods: enroll(), withdraw(), updateGpa(double newGpa), addCourse(), dropCourse()
- Add getter methods: getName(), getStudentId(), getGpa(), isEnrolled()
- Add a getProfile() method that returns a formatted String with all student information using getter methods
- Create a constructor that accepts name and studentId, initializes: gpa to 0.0, isEnrolled to false
- In your main method, instantiate at least 3 different Student objects with different information
- Demonstrate calling methods to change student status and use getters to access information
- Use string concatenation in your getProfile() method to create professional output
- Show how each Student object maintains its own independent field values
- Display profiles before and after making changes to demonstrate object state

**Expected Output Example:**
```
=== Student Profile Manager ===

Initial Student Profiles:
Alex Johnson (ID: 1001): GPA: 0.0, Status: Not Enrolled
Maria Garcia (ID: 1002): GPA: 0.0, Status: Not Enrolled  
David Chen (ID: 1003): GPA: 0.0, Status: Not Enrolled

=== Processing Student Changes ===
Enrolling Alex Johnson...
Updating Alex's GPA to 3.75...
Enrolling Maria Garcia...
Updating Maria's GPA to 3.92...

Updated Student Profiles:
Alex Johnson (ID: 1001): GPA: 3.75, Status: Enrolled
Maria Garcia (ID: 1002): GPA: 3.92, Status: Enrolled
David Chen (ID: 1003): GPA: 0.0, Status: Not Enrolled

Each Student object maintains independent data!
```