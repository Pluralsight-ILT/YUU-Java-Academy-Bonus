# **Exercises – Abstract Classes and Methods**

In these exercises you will create a Data Structure hierarchy using abstract classes and methods. You will practice defining abstract contracts for common data structure operations while requiring subclasses to implement their specific storage and retrieval logic.

Remember to create your projects in the pluralsight/workbook-5 folder.

---

## **EXERCISE 3**

Create a data structure framework using abstract classes to define common collection behavior while requiring subclasses to implement specific storage and access methods.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create an abstract `DataStructure` class with fields: name, size, maxCapacity
- Include concrete methods: isEmpty(), isFull(), getSize(), displayInfo()
- Define abstract methods: add(), remove(), contains(), clear()
- Create concrete subclasses: `Stack`, `Queue`, `Set` that extend DataStructure
- Implement abstract methods in each subclass:
    - Stack: LIFO (Last In, First Out) - add/remove from top
    - Queue: FIFO (First In, First Out) - add to rear, remove from front
    - Set: No duplicates allowed - check before adding
- Each subclass should use ArrayList internally but enforce their specific rules
- Create a DataStructureDemo class that demonstrates polymorphism with different structures
- Show that you cannot instantiate the abstract DataStructure class directly

**Expected Output Example:**
```
=== Data Structure Framework ===

Testing different data structures...

Stack Operations:
Adding: A, B, C
Stack contents: [A, B, C] (size: 3)
Removing from stack: C
Stack contents: [A, B] (size: 2)

Queue Operations:
Adding: X, Y, Z
Queue contents: [X, Y, Z] (size: 3)
Removing from queue: X
Queue contents: [Y, Z] (size: 2)

Set Operations:
Adding: 1, 2, 3, 2 (duplicate)
Set contents: [1, 2, 3] (size: 3)
Contains 2: true
Contains 5: false

All structures cleared.
Final sizes: Stack=0, Queue=0, Set=0
```