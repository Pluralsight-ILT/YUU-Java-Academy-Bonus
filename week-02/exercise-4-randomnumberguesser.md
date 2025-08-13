# **Exercises – Number Guessing Game**

In these exercises you will create a Number Guessing Game application. You will practice using while loops to create a game that continues until the user guesses correctly, along with input validation and user feedback.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 4**

Create a Java application named NumberGuessingGame. This application will generate a random number and use a while loop to keep prompting the user for guesses until they find the correct answer.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Generate a random number between 1 and 100 using Math.random()
- Use Scanner to collect user guesses in a while loop
- Provide feedback for each guess: "Too high", "Too low", or "Correct!"
- Count the number of attempts and display when the user wins
- Validate that user input is within the valid range (1-100)
- Use a while loop to continue until the correct number is guessed
- Display encouraging messages and final statistics
- Use string concatenation for clear, friendly user interaction

**Expected Output Example:**
```
=== Number Guessing Game ===
I'm thinking of a number between 1 and 100!

Enter your guess: 50
Too high! Try again.

Enter your guess: 25  
Too low! Try again.

Enter your guess: 37
Too high! Try again.

Enter your guess: 31
Too low! Try again.

Enter your guess: 34
Correct! You found it!

=== Game Results ===
The number was: 34
Total attempts: 5
Great job! You're a good guesser!
```