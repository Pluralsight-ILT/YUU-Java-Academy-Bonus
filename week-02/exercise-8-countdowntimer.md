# **Exercises: Countdown Timer**

Build a menu-driven countdown timer with three modes. Each mode uses a for loop with different step behavior and is organized into its own method.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 8**

Create a Java application named `CountdownTimer`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a `displayMenu()` method that shows the user three countdown options
- Use Scanner to collect the user's starting number and menu choice
- Implement these methods, each using a for loop:
  - `countdownByOnes(int start)`: counts down from start to 0
  - `countdownByFives(int start)`: counts down in steps of 5
  - `rocketLaunch()`: 10-second countdown with T-minus formatting
- Call the appropriate method based on user input
- `Thread.sleep()` between steps is optional
