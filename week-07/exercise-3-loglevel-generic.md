# **Exercises: Generic Logger with Log Levels**

Build a type-safe logging system using Java generics and inheritance, demonstrating how `<T extends LogLevel>` enforces level-specific loggers at compile time.

Remember to create your projects in the `pluralsight/workbook-6` folder.

---

## **Part 1**

Create a Java application named `generic-logger`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**

- Create an abstract class `LogLevel` with:
  - A `getLabel()` method that returns a `String` (e.g. `"ERROR"`)
  - A `getSeverity()` method that returns an `int` representing priority (e.g. `1` for Debug, `4` for Error)
- Create four concrete subclasses: `Debug`, `Info`, `Warning`, `Error` — each implementing both methods with appropriate values
- Define a generic class `Logger<T extends LogLevel>` with:
  - A `log(String message, T level)` method that prints a formatted log entry
- In `main()`, create a `Logger<Error>` and a `Logger<Debug>`, and call `log()` on each

**Expected Output (example):**
```
[ERROR] (severity: 4) NullPointerException in OrderService
[DEBUG] (severity: 1) Entering method: calculateTotal()
```

---

## **Part 2**

Prove the type safety that generics give you.

**Requirements:**

- In `main()`, attempt to pass a `Debug` instance to your `Logger<Error>` — observe the **compiler error** it produces
- Comment out that line once you've seen the error, with a note explaining *why* the compiler rejected it
- Add a `logMultiple(List<String> messages, T level)` method to `Logger<T>` that logs a list of messages all at the same level
- Call `logMultiple()` from `main()` with at least three messages

**Expected Output (example):**
```
[WARNING] (severity: 3) Disk usage above 80%
[WARNING] (severity: 3) Memory usage above 90%
[WARNING] (severity: 3) CPU spike detected
```