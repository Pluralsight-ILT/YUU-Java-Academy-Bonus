# **Exercises – Interfaces with Universal TV Remote**

In these exercises you will create a Universal TV Remote system using interfaces to control different types of electronic devices. You will practice defining contracts through interfaces and implementing them across various device types.

Remember to create your projects in the pluralsight/workbook-6 folder.

---

## **EXERCISE 1**

Create a universal remote control system using interfaces to define common device operations. Build upon previous TV remote concepts to demonstrate how interfaces allow different devices to be controlled uniformly.

**Remember:** When creating a new Java project, create a new git repository and commit your changes often! Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a `Controllable` interface with methods: turnOn(), turnOff(), changeChannel(int channel), adjustVolume(int level)
- Create a `Streamable` interface with methods: connectToWifi(String network), selectApp(String app), playContent(String title)
- Create device classes that implement appropriate interfaces:
    - `SmartTV` class: Implements both Controllable and Streamable
        - Fields: brand, currentChannel, volume, isOn, connectedNetwork, currentApp
        - Can do everything - power, channels, volume, streaming
    - `SoundSystem` class: Implements only Controllable 
        - Fields: brand, volume, isOn (no channels - use channel methods to show "not supported")
        - Focus on audio - power and volume control only
    - `StreamingBox` class: Implements only Streamable
        - Fields: brand, connectedNetwork, currentApp (no power/volume - always on)
        - Streaming only - apps and content, no traditional TV controls
- Create a `UniversalRemote` class with methods to control any Controllable device
- In main method, create instances of each device and demonstrate interface polymorphism
- Show how the same interface methods work differently on different devices
- Display what happens when devices don't support certain operations

**Expected Output Example:**
```
=== Universal Remote Control System ===

Creating devices...

Samsung Smart TV:
- Turning on... Smart TV is now ON
- Changing to channel 25... Now watching ESPN
- Adjusting volume to 15... Volume set to 15
- Connecting to WiFi... Connected to HomeNetwork
- Selecting Netflix app... Netflix launched
- Playing "Stranger Things"... Now streaming content

Bose Sound System:
- Turning on... Sound system powered on
- Changing channel... NOT SUPPORTED (audio device only)
- Adjusting volume to 20... Premium audio at volume 20
- Cannot stream (does not implement Streamable)

Apple TV Streaming Box:
- Always on (no power controls)
- No volume control (uses TV speakers)
- Connecting to WiFi... Connected to HomeNetwork
- Selecting Disney+ app... Disney+ app opened
- Playing "The Mandalorian"... Now streaming content

Universal Remote Test:
- Successfully controlled 2 Controllable devices
- Smart TV supports both interfaces
- Each device handles methods according to its capabilities
```