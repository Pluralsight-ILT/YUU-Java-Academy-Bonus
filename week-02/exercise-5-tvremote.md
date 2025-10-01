# **Exercises – TV Remote Object Basics**

In these exercises you will create a TV Remote application. You will practice creating a simple class with fields and methods, and learn how to instantiate objects and use their properties to control a television.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 5**

Create a Java application named TVRemoteApp. This application will demonstrate basic object-oriented programming by creating a TVRemote class with fields and methods, then instantiate objects to control different televisions.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**

- Create a TVRemote class with appropriate fields to track: the manufacturer name, current channel number, current volume level, and power state
- Include basic methods: `turnOn()`, `turnOff()`, `changeChannel(int newChannel)`, `adjustVolume(int newVolume)`
- Add a `getChannelName()` method that returns the name of the channel based on the current channel number. Your application should recognize and display names for channels 3, 5, 8, 13, and 25. For any other channel number, return a generic message
- Add getter methods for all your fields
- Add a `getStatus()` method that returns a description of the current TV state using the getter methods, and include the channel name in the output
- Create a constructor that accepts the manufacturer name and initializes appropriate starting values for a new television
- In your main method, instantiate at least 2 different TVRemote objects with different manufacturers
- Demonstrate calling methods to change the TV state and use getters to display current information
- Test the `getChannelName()` method by changing to different channels, including at least channels 3, 8, 13, 25, and one channel that isn't recognized
- Use string concatenation in your `getStatus()` method to format the output
- Show how each TVRemote object maintains its own independent field values
- Display the status before and after using TVRemote methods

**Channel Mappings to Use:**
- Channel 3: Cartoon Network
- Channel 5: Discovery Channel
- Channel 8: Food Network
- Channel 13: HGTV
- Channel 25: Comedy Central
- Any other channel: "Unknown Channel"

**Expected Output Example:**

```
=== TV Remote Control System ===

Living Room TVRemote (Samsung):
Status: OFF, Channel: 1 (Unknown Channel), Volume: 10

Bedroom TVRemote (LG):  
Status: OFF, Channel: 1 (Unknown Channel), Volume: 10

=== Using the Living Room TVRemote ===
Turning on TV...
Changing to channel 25...
Now watching: Comedy Central
Setting volume to 15...

Updated Status: ON, Channel: 25 (Comedy Central), Volume: 15

=== Using the Bedroom TVRemote ===
Turning on TV...
Changing to channel 8...
Now watching: Food Network
Setting volume to 12...
Changing to channel 3...
Now watching: Cartoon Network
Changing to channel 99...
Now watching: Unknown Channel

Updated Status: ON, Channel: 99 (Unknown Channel), Volume: 12

Both TVRemote objects control independent TVs!
```