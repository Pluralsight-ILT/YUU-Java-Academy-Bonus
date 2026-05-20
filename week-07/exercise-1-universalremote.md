# **Exercises: Interfaces with Universal TV Remote**

In these exercises you will create a Universal TV Remote system using interfaces to control different types of electronic devices. You will practice defining contracts through interfaces and implementing them across various device types.

Remember to create your projects in the pluralsight/workbook-6 folder.

---

## **EXERCISE 1**

Create a Java application named `universal-remote` that uses interfaces to define common device operations. Build upon previous TV remote concepts to demonstrate how interfaces allow different devices to be controlled uniformly.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

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

Samsung Smart TV (Controllable + Streamable):
- Power: ON, Channel: 25 (ESPN), Volume: 15
- WiFi: HomeNetwork, App: Netflix, Playing: "Stranger Things"

Bose Sound System (Controllable):
- Power: ON, Volume: 20
- Channel change: NOT SUPPORTED (audio device only)
- Streaming: NOT SUPPORTED

Apple TV Streaming Box (Streamable):
- Always on, no volume control
- WiFi: HomeNetwork, App: Disney+, Playing: "The Mandalorian"

Universal Remote: controlled 2 Controllable devices.
```