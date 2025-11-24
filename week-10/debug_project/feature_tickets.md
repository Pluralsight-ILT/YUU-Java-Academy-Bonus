# MangoMusic JDBC Application - Feature Requests

These are new reports that need to be built from scratch. You'll need to:
1. Write the SQL query
2. Add a method to `MangoMusicDataManager.java`
3. Update the appropriate screen class to call your new method and display results

---

## **Feature #1: Most Played Albums by Genre**
**Priority:** Medium  
**Component:** Special Reports  
**Requested By:** Content Acquisition Team

**Business Need:**
The content team wants to see the top performing albums within each genre. This will help them identify which albums to promote heavily and which genres need more quality content. Instead of just knowing the top albums overall, they want to see the leaders in each genre category.

**Feature Description:**
Create a report that shows the top 5 most-played albums for each genre. This means if we have 10 genres, we'll see 50 albums total (5 per genre). This helps identify genre-specific hits that might not make the overall top albums list.

**Required Output Columns:**
- `genre` - The music genre
- `album_title` - Name of the album
- `artist_name` - Name of the artist
- `play_count` - Number of times this album was played
- `genre_rank` - Rank within the genre (1-5)

**Business Rules:**
- Show top 5 albums per genre based on play count
- Include all genres that have at least 5 albums with plays
- Within each genre, rank by play_count descending
- Use all historical play data (no date filter)

**Technical Requirements:**
1. **SQL Query:** You'll need to use a subquery or window function approach. Consider:
   - Grouping plays by genre and album
   - Ranking albums within each genre
   - Filtering to top 5 per genre
   
2. **DAO Method:** Add to `MangoMusicDataManager.java`:
```java
   public List<ReportResult> getMostPlayedAlbumsByGenre()
```

3. **UI Integration:** Update `SpecialReportsScreen.java`:
   - Replace the "not implemented" message in `showMostPlayedAlbumsByGenre()`
   - Call your new DAO method
   - Display results in a formatted table

**Expected Output Format:**
```
Genre           Album Title                    Artist Name              Play Count  Rank
Rock            Abbey Road                     The Beatles              1,245       1
Rock            Led Zeppelin IV                Led Zeppelin            987         2
Rock            Dark Side of the Moon          Pink Floyd              856         3
...
Pop             1989                           Taylor Swift            2,341       1
Pop             Thriller                       Michael Jackson         1,987       2
```

**Verification Steps:**
1. Run the report and verify each genre shows exactly 5 albums (or fewer if genre has < 5 albums)
2. Within each genre, verify albums are ranked by play_count descending
3. Verify the rank resets to 1 for each new genre
4. Compare top album for a genre to manual query: 
```sql
   SELECT genre, album, COUNT(*) 
   FROM album_plays ap 
   JOIN albums al ON ap.album_id = al.album_id 
   JOIN artists ar ON al.artist_id = ar.artist_id 
   WHERE ar.primary_genre = 'Rock' 
   GROUP BY al.album_id 
   ORDER BY COUNT(*) DESC 
   LIMIT 5;
```

**Success Criteria:**
- Report displays top 5 albums per genre
- Rankings are accurate within each genre
- All genres with sufficient data are represented
- Content team can identify genre-specific hits

**Hints:**
- You might need to use a subquery that ranks albums within genres
- The SQL `ROW_NUMBER()` window function or a derived table with ranking logic could be useful
- Remember to join album_plays → albums → artists to get genre information

---

## **Feature #2: User Listening Diversity Score**
**Priority:** Medium  
**Component:** Special Reports  
**Requested By:** Personalization Team

**Business Need:**
The personalization team wants to segment users into "explorers" (who listen to many different genres) versus "specialists" (who stick to a few genres). This will help customize recommendations - explorers should see variety, specialists should see deep cuts in their preferred genres.

**Feature Description:**
Create a report that calculates a "diversity score" for users based on how many different genres and artists they listen to. Users who listen to many genres but fewer artists per genre score higher (true explorers). Users who listen to many artists but only in 1-2 genres score lower (specialists).

**Required Output Columns:**
- `user_id` - User identifier
- `username` - Username for reference
- `subscription_type` - Free or premium
- `distinct_genres_played` - Count of unique genres this user has listened to
- `distinct_artists_played` - Count of unique artists this user has listened to
- `total_plays` - Total lifetime plays for context
- `diversity_score` - Calculated score (see formula below)

**Business Rules:**
- Only include users with at least 20 total plays (enough data for meaningful score)
- Diversity score formula: `(distinct_genres / distinct_artists) * 100`
- Higher score = listens to multiple genres with fewer artists per genre (explorer)
- Lower score = listens to many artists but in few genres (specialist)
- Show top 100 most diverse users
- Order by diversity_score descending

**Technical Requirements:**
1. **SQL Query:** You'll need to:
   - Join album_plays → albums → artists → users
   - Count distinct genres per user
   - Count distinct artists per user
   - Calculate the diversity score
   - Filter to users with 20+ plays
   
2. **DAO Method:** Add to `MangoMusicDataManager.java`:
```java
   public List<ReportResult> getUserDiversityReport()
```

3. **UI Integration:** Update `SpecialReportsScreen.java`:
   - Replace the "not implemented" message in `showUserDiversityScore()`
   - Call your new DAO method
   - Display results in a formatted table
   - Consider showing score interpretation (>15 = explorer, <5 = specialist)

**Expected Output Format:**
```
User ID  Username        Subscription  Genres  Artists  Total Plays  Diversity Score
42       music_lover     premium       8       15       245          53.33
103      explorer99      free          6       12       189          50.00
...
```

**Interpretation Guide:**
- Score > 15: True explorer - likes many genres, samples different artists
- Score 5-15: Balanced listener - explores but has favorites
- Score < 5: Genre specialist - deep dives into specific genres

**Verification Steps:**
1. Pick a user from the results and manually verify their genre/artist counts:
```sql
   SELECT 
       COUNT(DISTINCT ar.primary_genre),
       COUNT(DISTINCT ar.artist_id)
   FROM album_plays ap
   JOIN albums al ON ap.album_id = al.album_id
   JOIN artists ar ON al.artist_id = ar.artist_id
   WHERE ap.user_id = 42;
```
2. Manually calculate their diversity score and verify it matches
3. Confirm all users have at least 20 plays
4. Verify users are ordered by diversity_score descending

**Success Criteria:**
- Report calculates diversity score correctly
- Only includes users with sufficient play history
- Scores make logical sense (users with more genres have higher scores)
- Helps personalization team segment users for recommendations

**Hints:**
- You'll need to COUNT(DISTINCT ...) for both genres and artists
- The diversity formula needs to be calculated in SQL as: (genre_count * 100.0 / artist_count)
- Don't forget to use 100.0 instead of 100 to get decimal results
- Use HAVING clause to filter to users with 20+ plays

---

## **Feature #3: Peak Listening Hours Analysis**
**Priority:** Low  
**Component:** Special Reports  
**Requested By:** Product Team

**Business Need:**
The product team wants to understand what time of day users are most active on the platform. This will help them decide:
- When to schedule new album releases for maximum impact
- Optimal times to send push notifications
- When to run promotional campaigns
- Server capacity planning for peak hours

**Feature Description:**
Create a report showing listening activity broken down by hour of the day (0-23). This reveals peak usage times and quiet periods, helping the team optimize their scheduling and infrastructure.

**Required Output Columns:**
- `hour_of_day` - Hour in 24-hour format (0-23)
- `total_plays` - Total album plays during this hour across all days
- `unique_users` - Count of distinct users who played during this hour
- `avg_plays_per_user` - Average plays per user during this hour

**Business Rules:**
- Group all plays by the hour they occurred (use HOUR() function on played_at)
- Include all historical play data (no date filter)
- Show all 24 hours (0-23) even if some have no plays
- Order by hour_of_day ascending (midnight to 11 PM)

**Technical Requirements:**
1. **SQL Query:** You'll need to:
   - Extract hour from played_at timestamp using HOUR()
   - Group by hour
   - Count total plays per hour
   - Count distinct users per hour
   - Calculate average plays per user
   
2. **DAO Method:** Add to `MangoMusicDataManager.java`:
```java
   public List<ReportResult> getPeakListeningHoursReport()
```

3. **UI Integration:** Update `SpecialReportsScreen.java`:
   - Replace the "not implemented" message in `showPeakListeningHours()`
   - Call your new DAO method
   - Display results in a formatted table
   - Consider adding visual indicators for peak hours (if plays > avg)

**Expected Output Format:**
```
Hour    Total Plays    Unique Users    Avg Plays/User
00      1,245          342             3.64
01      892            256             3.48
02      654            198             3.30
...
12      3,456          876             3.95
13      4,123          1,023           4.03
14      3,890          967             4.02
...
```

**Expected Insights:**
- Likely to see peaks during:
  - Morning commute (7-9 AM)
  - Lunch time (12-1 PM)
  - Evening commute (5-7 PM)
  - Late evening (9-11 PM)
- Likely to see lows during:
  - Late night/early morning (2-6 AM)
  - Mid-afternoon work hours (2-4 PM)

**Verification Steps:**
1. Verify all 24 hours (0-23) are represented
2. Check that hours are in ascending order
3. For a specific hour, verify counts manually:
```sql
   SELECT 
       COUNT(*) as total_plays,
       COUNT(DISTINCT user_id) as unique_users
   FROM album_plays
   WHERE HOUR(played_at) = 14;
```
4. Confirm avg_plays_per_user = total_plays / unique_users for each row

**Success Criteria:**
- Report shows all 24 hours
- Play counts and user counts are accurate
- Averages are calculated correctly
- Product team can identify peak usage times for optimization

**Hints:**
- Use the HOUR() function to extract hour from TIMESTAMP: `HOUR(played_at)`
- Hour values range from 0 (midnight) to 23 (11 PM)
- Group by the hour extracted from played_at
- Calculate average as: COUNT(play_id) / COUNT(DISTINCT user_id)
- Use ROUND() to format the average to 2 decimal places

---

## **Implementation Guidelines**

**For Each Feature:**

1. **Start with SQL First:**
   - Write and test your query directly in MySQL Workbench
   - Verify results match requirements
   - Make sure column names match what you'll use in Java

2. **Add DAO Method:**
   - Follow the pattern of existing report methods in `MangoMusicDataManager`
   - Use try-with-resources for all JDBC resources
   - Use PreparedStatement even if no parameters (good practice)
   - Return `List<ReportResult>`
   - Handle SQLExceptions appropriately

3. **Update UI Screen:**
   - Find the corresponding method in `SpecialReportsScreen.java`
   - Call your new DAO method
   - Format and display results similar to existing reports
   - Add appropriate headers and formatting

4. **Test Thoroughly:**
   - Run the report and verify output looks correct
   - Compare results to manual SQL queries
   - Test edge cases (empty results, large result sets)
   - Verify error handling works

**Code Quality Checklist:**
- [ ] SQL query is efficient and follows best practices
- [ ] Using try-with-resources for all JDBC objects
- [ ] PreparedStatement used (even without parameters)
- [ ] ResultSet columns named clearly
- [ ] Error messages are helpful
- [ ] Results are formatted nicely for users
- [ ] Code follows existing patterns in the project

**Common Mistakes to Avoid:**
- Forgetting to close ResultSet, PreparedStatement, or Connection
- Misspelling ResultSet column names
- Not handling null values properly
- Integer division losing decimal precision
- Not testing with actual database data
- Copying code without understanding it

**Getting Help:**
- Review existing working report methods for patterns
- Check JDBC documentation for ResultSet methods
- Test SQL queries separately before adding to Java
- Use descriptive variable names for clarity