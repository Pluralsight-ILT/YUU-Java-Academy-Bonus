# **Exercises – Inheritance and Method Overriding**

In these exercises you will create a Musical Instrument hierarchy that demonstrates inheritance, method overriding, and polymorphism. You will practice extending classes and customizing behavior through method overriding.

Remember to create your projects in the pluralsight/workbook-5 folder.

---

## **EXERCISE 2**

Create a music store management system using inheritance to model different types of instruments. Demonstrate how subclasses can extend and customize parent class behavior.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create an `Instrument` base class with fields: brand, model, price, type
- Include methods: tune(), play(), getInfo()
- Create subclasses: `Guitar`, `Piano`, `Drum` that extend Instrument
- Override methods in subclasses to provide specific behavior:
    - Guitar: Has number of strings, different tuning process
    - Piano: Has number of keys, different playing technique
    - Drum: Has drum type, different sound production
- Use `super()` in constructors to call parent constructor
- Override `toString()` method in each class for proper display
- Create a main method that demonstrates polymorphism with Instrument references

**Expected Output Example:**
```
=== Music Store Inventory ===

Adding instruments to inventory...

Fender Stratocaster (Guitar):
Tuning guitar strings... All 6 strings in tune!
Playing guitar... Beautiful chord progression!
Price: $899.99, Strings: 6

Yamaha Grand Piano (Piano):  
Tuning piano keys... All 88 keys perfectly tuned!
Playing piano... Melodic notes flowing!
Price: $12999.99, Keys: 88

Pearl Drum Kit (Drum):
Setting up drum kit... Snare drum ready!
Playing drums... Powerful rhythmic beats!
Price: $1599.99, Type: Acoustic

Total instruments: 3
Combined value: $15499.97
```