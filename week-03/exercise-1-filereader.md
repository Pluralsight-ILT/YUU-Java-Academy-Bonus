# **Exercises – File Reader with Error Handling**

In these exercises you will create a File Reader application. You will practice using BufferedReader to read text files and implement try/catch blocks for proper error handling.

Remember to create your projects in the pluralsight/workbook-3 folder.

---

## **EXERCISE 1**

Create a Java application named FileReader. This application will read a text file using BufferedReader and handle potential file errors with try/catch blocks.

You will be required to create your own file and add whatever you would like. We recommend using a text file format.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Use Scanner to prompt for a filename
- Create a readFile() method that uses BufferedReader and FileReader to read the file
- Implement try/catch blocks to handle FileNotFoundException and IOException
- Count the total lines and characters while reading
- Display the file contents along with statistics
- Use proper resource management (close the BufferedReader)
- Handle both successful file reading and error cases with appropriate messages

**Expected Output Example:**
```
=== File Reader ===

Enter filename: sample.txt

=== File Contents ===
Line 1: Hello World
Line 2: This is a test file
Line 3: Java programming is fun

=== File Statistics ===
Total lines: 3
Total characters: 58
File read successfully!
```