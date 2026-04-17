# **Exercises: File Reader with Error Handling**

Read a text file using `BufferedReader`, count lines and characters, and handle file errors with try/catch.

Create your own text file with whatever content you want before running the program.

Remember to create your projects in the pluralsight/workbook-3 folder.

---

## **EXERCISE 1**

Create a Java application named `FileReader`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Use Scanner to prompt for a filename
- Create a `readFile()` method using `BufferedReader` and `FileReader` to read and print the file contents
- Count total lines and characters while reading
- Handle `FileNotFoundException` and `IOException` with try/catch
- Close the `BufferedReader` when done
- Print file statistics after reading
