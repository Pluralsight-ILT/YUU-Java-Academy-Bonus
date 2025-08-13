# **Exercises – TV Remote Object Basics**

In these exercises you will create a TV Remote application. You will practice creating a simple class with fields and methods, and learn how to instantiate objects and use their properties to control a television.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 5**

Create a Java application named TVRemoteApp. This application will demonstrate basic object-oriented programming by creating a TVRemote class with fields and methods, then instantiate objects to control different televisions.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a TVRemote class with fields: brand (String), channel (int), volume (int), isOn (boolean)
- Include basic methods: turnOn(), turnOff(), changeChannel(int newChannel), adjustVolume(int newVolume)
- Add getter methods for all your fields
- Add a getStatus() method that returns a String describing the current TV state using the getter methods
- Create a constructor that accepts a String brand and initializes: channel to 1, volume to 10, isOn to false
- In your main method, instantiate at least 2 different TVRemote objects with different brands
- Demonstrate calling methods to change the TV state and use getters to display current information
- Use string concatenation in your getStatus() method to format the output
- Show how each TVRemote object maintains its own independent field values
- Display the status before and after using TVRemote methods

**Expected Output Example:**
```
=== TV Remote Control System ===

Living Room TVRemote (Samsung):
Status: OFF, Channel: 1, Volume: 10

Bedroom TVRemote (LG):  
Status: OFF, Channel: 1, Volume: 10

=== Using the Living Room TVRemote ===
Turning on TV...
Changing to channel 25...
Setting volume to 15...

Updated Status: ON, Channel: 25, Volume: 15

=== Using the Bedroom TVRemote ===
Turning on TV...
Changing to channel 8...

Updated Status: ON, Channel: 8, Volume: 10

Both TVRemote objects control independent TVs!
```