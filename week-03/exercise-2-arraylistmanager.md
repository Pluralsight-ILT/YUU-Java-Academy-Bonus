# **Exercises – ArrayList Manager**

In these exercises you will create an ArrayList Manager application. You will practice using ArrayList methods to add, remove, search, and modify elements dynamically.

Remember to create your projects in the pluralsight/workbook-3 folder.

---

## **EXERCISE 2**

Create a Java application named ArrayListManager. This application will demonstrate ArrayList operations with a collection of student names.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create an ArrayList<String> to store student names
- Use Scanner to add multiple student names to the list
- Create methods: addStudent(), removeStudent(), searchStudent(), displayAllStudents()
- Implement a menu system allowing users to choose operations
- Display the list contents before and after modifications
- Handle cases where student names are not found
- Show how ArrayList size changes dynamically

**Expected Output Example:**
```
=== ArrayList Manager ===

Current students: [Alice, Bob, Charlie, Diana]

Menu:
1. Add student
2. Remove student  
3. Search student
4. Display all

Enter choice: 2
Enter name to remove: Bob

Updated students: [Alice, Charlie, Diana]
Bob removed successfully!
Current size: 3
```