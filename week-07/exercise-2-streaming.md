# **Exercises: Streams with TV Channel Data**

In these exercises you will create a TV Channel Analytics system using Java Streams to process and analyze television programming data. You will practice filtering, mapping, sorting, and collecting data using stream operations.

Remember to create your projects in the pluralsight/workbook-6 folder.

---

## **EXERCISE 2**

Create a Java application named `channel-analytics` that uses Java Streams to process channel and show data. Learn to use stream operations for data filtering, transformation, and aggregation.

**Remember:** When creating a new Java project, create a new git repository and **commit your changes often!** Don't forget to push to github.com. Always create the Github repository before creating a new Java project.

**Requirements:**
- Create a `TVShow` class with fields: title, genre, rating, duration, channel, isOriginal
- Create a `Channel` class with fields: name, category, shows (List of TVShow objects)
- Populate sample data with at least 15 shows across 4-5 different channels
- Use Java Streams to implement these analytics methods:
    - Find all shows longer than 60 minutes
    - Get shows by specific genre (Comedy, Drama, News, etc.)
    - Find top-rated shows (rating above 8.0)
    - Calculate average show duration by channel
    - Group shows by genre and count them
    - Find channels with most original programming
    - Get the longest show on each channel
- Create a `ChannelAnalytics` class that processes this data using stream operations
- Display results in a formatted, easy-to-read manner

**Expected Output Example:**
```
=== TV Channel Analytics ===

Loaded: 15 shows across 5 channels.

Shows longer than 60 minutes:
- Game of Thrones (HBO) — 65 min, 9.2
- The Crown (Netflix) — 70 min, 8.8
- Breaking Bad (AMC) — 62 min, 9.5

Comedy Shows:
- The Office (NBC) — 22 min, 8.9
- Parks and Recreation (NBC) — 22 min, 8.6
- Brooklyn Nine-Nine (FOX) — 23 min, 8.4

Top-Rated Shows (8.0+):
1. Breaking Bad — 9.5
2. Game of Thrones — 9.2
3. The Office — 8.9
4. The Crown — 8.8

Average Duration by Channel:
- HBO: 52.5 min
- Netflix: 45.0 min
- NBC: 28.3 min
- AMC: 48.7 min

Shows by Genre:
- Drama: 6, Comedy: 4, Action: 3, Documentary: 2

Channels with Most Originals:
1. Netflix: 4
2. HBO: 3
3. NBC: 2
```