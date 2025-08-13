# **Exercises – Objects and Basic Encapsulation**

In these exercises you will create a simple MenuItem class to understand what objects are and how encapsulation protects data. You will practice creating objects, accessing them safely, and understanding the difference between classes and objects.

Remember to create your projects in the pluralsight/workbook-5 folder.

---

## **EXERCISE 1**

Create a MenuItem class that demonstrates basic object creation and encapsulation principles. Learn the fundamental concepts of what objects are and why we protect their data.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a `MenuItem` class with private fields: name, price, category, isAvailable
- Add a constructor that takes name, price, and category parameters (isAvailable defaults to true)
- Create getter methods: getName(), getPrice(), getCategory(), isAvailable()
- Create setter methods: setPrice(), setAvailable() (but NOT setName or setCategory - these shouldn't change)
- Add a method: getMenuInfo() that returns a formatted string with all information
- Create a main method that creates multiple MenuItem objects
- Demonstrate that you cannot access private fields directly
- Show how getter and setter methods provide controlled access
- Create at least 4 different menu items and display their information

**Expected Output Example:**
```
=== Restaurant Menu Demo ===

Creating menu items...

Item 1: Cheeseburger - $12.99 (Main Course) [Available]
Item 2: Caesar Salad - $8.50 (Appetizer) [Available]
Item 3: Chocolate Cake - $6.75 (Dessert) [Available]
Item 4: Grilled Salmon - $18.99 (Main Course) [Available]

Updating prices and availability...
Chocolate Cake is now sold out.
Cheeseburger price updated to $13.99.

Updated Menu:
Item 1: Cheeseburger - $13.99 (Main Course) [Available]
Item 3: Chocolate Cake - $6.75 (Dessert) [Sold Out]

Attempting direct field access: [Compilation Error - Cannot access private field]

Total menu items: 4
```