# **Exercises: TV Remote**

Build a `TVRemote` class with fields and methods, then instantiate multiple objects and show that each one tracks its own state independently.

Remember to create your projects in the pluralsight/workbook-2 folder.

---

## **EXERCISE 5**

Create a Java application named `TVRemoteApp`.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**`TVRemote` class requirements:**
- Fields: manufacturer name, current channel, current volume, power state
- Constructor: accepts manufacturer name, sets appropriate starting values
- Methods: `turnOn()`, `turnOff()`, `changeChannel(int newChannel)`, `adjustVolume(int newVolume)`
- `getChannelName()`: returns the channel name based on current channel number
- Getter methods for all fields
- `getStatus()`: returns a formatted string describing current state, including channel name

**Channel mappings:**
- 3: Cartoon Network
- 5: Discovery Channel
- 8: Food Network
- 13: HGTV
- 25: Comedy Central
- Any other channel: "Unknown Channel"

**In `main`:**
- Instantiate at least 2 `TVRemote` objects with different manufacturers
- Use methods on each to change channel and volume
- Display status before and after changes
- Test `getChannelName()` with known channels and at least one unknown channel
- Show that each object maintains its own independent state
