# **Exercises – Text Analyzer**

In these exercises you will create a Text Analyzer application. You will use string methods to analyze and extract information from user-provided text, counting characters, finding patterns, and generating detailed text statistics.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 3**

Create a Java application named TextAnalyzer. This application will analyze text input from the user and provide detailed statistics using various string methods to examine the content.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Use Scanner to prompt the user for a sentence or paragraph to analyze
- Use string methods to count total characters, characters without spaces, and word count
- Find the first and last occurrence of a specific character using indexOf() and lastIndexOf()
- Check if the text contains certain words using contains() method
- Extract the first word, last word, and middle portion of the text using substring()
- Analyze the text for uppercase vs lowercase letters
- Create a summary report with all findings using string concatenation
- Allow user to search for a specific word and report its position

**Expected Output Example:**
```
=== Text Analyzer ===

Enter text to analyze: Programming in Java requires practice and patience

=== Analysis Results ===
Total characters: 46
Characters (no spaces): 39
Estimated word count: 7

=== Character Analysis ===
First character: 'P'
Last character: 'e'
Contains 'Java': true
Contains 'Python': false

=== Word Extraction ===
First word: "Programming"
Last word: "patience"
Middle section: "Java requires practice"

=== Search Results ===
Position of 'a' first occurrence: 13
Position of 'a' last occurrence: 44
Word 'practice' found at position: 25
```