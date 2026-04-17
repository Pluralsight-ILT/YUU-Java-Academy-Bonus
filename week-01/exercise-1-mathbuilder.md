# **Exercises: Mathematical Expression Builder**

Java evaluates operators in a specific order, and that order changes your result. Write a program that evaluates the same values two different ways and prints both results so the difference is clear.

Remember to create your projects in the pluralsight/workbook-1 folder.

---

## **EXERCISE 1**

Create a Java application named `MathExpressionBuilder`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

Start by declaring these four variables:

```java
int x = 12;
int y = 7;
int z = 2;
double w = 1.8;
```

Now work through two versions of a calculation and print both results so you can compare them.

**Version 1:** evaluate this expression:

```
x + y * z - w
```

Before you run it, work it out by hand. What does Java do first? Then write the code two ways: once as a single expression, and once broken into intermediate variables that show each step. Both should give you the same answer. If they don't, something's off in your step-by-step.

**Version 2:** now add parentheses:

```
(x + y) * z - w
```

Same values, different grouping. Print this result alongside the first one.

Your output should make it clear to anyone reading it what each number represents. You decide the format. Just make it readable. The two results are different, and that difference is the whole point.

Once you have it working, can you explain in a comment or in your output label *why* they differ?
