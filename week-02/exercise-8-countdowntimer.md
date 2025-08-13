# **Exercises – Countdown Timer**

In these exercises you will create a Countdown Timer application. You will practice using for loops with different increments and demonstrate how methods can organize your code while creating formatted timer displays.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 8**

Create a Java application named CountdownTimer. This application will use for loops to create different types of countdown timers and organize the functionality using methods.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Use Scanner to get the starting number for the countdown
- Create a countdownByOnes() method that counts down from the number to 0 using a for loop
- Create a countdownByFives() method that counts down by 5s using a for loop
- Create a rocketLaunch() method that does a 10-second countdown with special formatting
- Use string concatenation to format the timer displays attractively
- Add delays between numbers using Thread.sleep() if desired (optional)
- Create a displayMenu() method that shows options to the user
- In main method, call the appropriate countdown method based on user choice
- Demonstrate how for loops can count in different increments (positive and negative)

**Expected Output Example:**
```
=== Countdown Timer ===

Choose countdown type:
1. Count by ones
2. Count by fives  
3. Rocket launch sequence

Enter choice: 3

=== Rocket Launch Sequence ===
T-minus 10...
T-minus 9...
T-minus 8...
T-minus 7...
T-minus 6...
T-minus 5...
T-minus 4...
T-minus 3...
T-minus 2...
T-minus 1...
🚀 LIFTOFF! 🚀

Mission successful!
```