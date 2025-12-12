# MangoMusic API - Bonus Feature Tickets

These are additional features to extend the MangoMusic API beyond the core requirements. Implement these features to practice more advanced Spring Boot development, complex SQL queries, and business logic.

---

## **Feature #6: User Listening Streak**
**Difficulty:** Easy  
**Endpoint:** GET /api/users/{userId}/listening-streak  
**Estimated Time:** 30-45 minutes

### Business Need
Track user engagement by showing their current listening streak (consecutive days with at least one play). This gamification feature encourages daily usage and helps identify highly engaged users.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/users/{userId}/listening-streak`
- Path Parameter: userId (integer)

**Response Format:**
```json
{
  "userId": 1,
  "username": "musiclover42",
  "currentStreak": 5,
  "longestStreak": 12,
  "lastPlayDate": "2025-12-10"
}
```

**HTTP Status Codes:**
- 200 OK - Success with streak data
- 404 Not Found - User doesn't exist

### Business Rules
1. Streak breaks if user misses a full day (no plays between 00:00:00 and 23:59:59)
2. Only count completed plays (completed = true)
3. Today counts toward current streak if user has played today
4. Return 404 if user doesn't exist
5. Return currentStreak: 0 and longestStreak: 0 if user has no plays
6. lastPlayDate should be null if no plays exist

### Implementation Guide

**Controller Method:**
```java
@GetMapping("/{id}/listening-streak")
public ResponseEntity<Map<String, Object>> getListeningStreak(@PathVariable int id)
```

**Service Method:**
```java
public Map<String, Object> getListeningStreak(int userId)
```
- Validate user exists (return null if not)
- Call DAO to get play dates
- Calculate current streak by checking consecutive dates
- Calculate longest streak from historical data
- Build response map

**DAO Method:**
```java
public List<LocalDate> getUserPlayDates(int userId)
```
- Query: Get DISTINCT DATE(played_at) for user where completed = true
- Order by date DESC
- Return as List<LocalDate>

**Algorithm for Current Streak:**
1. Get list of distinct play dates (most recent first)
2. Start with today's date
3. Check if user played today - if yes, streak = 1, if no, streak = 0
4. Walk backwards through dates, incrementing streak for each consecutive day
5. Stop when you find a gap (missing day)

**Algorithm for Longest Streak:**
1. Walk through all play dates chronologically
2. Track current running streak
3. When gap found, reset running streak
4. Keep track of maximum streak seen

### SQL Hints
```sql
-- Get distinct play dates for user
SELECT DISTINCT DATE(played_at) as play_date
FROM album_plays
WHERE user_id = ? AND completed = true
ORDER BY play_date DESC;
```

### Testing
**Test Cases:**
1. User with 5-day current streak → Returns currentStreak: 5
2. User who played yesterday but not today → Returns currentStreak: 0
3. User with longest streak of 30 days → Returns longestStreak: 30
4. User with no plays → Returns currentStreak: 0, longestStreak: 0
5. Non-existent user → Returns 404

### Success Criteria
- ✅ Returns correct current streak based on consecutive days
- ✅ Returns correct longest streak from all-time history
- ✅ Handles users who haven't played today correctly
- ✅ Returns 404 for non-existent users
- ✅ Only counts completed plays

---

## **Feature #7: Album Completion Rate**
**Difficulty:** Easy  
**Endpoint:** GET /api/albums/{albumId}/completion-rate  
**Estimated Time:** 30-45 minutes

### Business Need
Help artists and labels understand listener engagement. High completion rates indicate engaging content, while low rates might suggest issues with album quality or length.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/albums/{albumId}/completion-rate`
- Path Parameter: albumId (integer)

**Response Format:**
```json
{
  "albumId": 42,
  "title": "Dark Side of the Moon",
  "artistName": "Pink Floyd",
  "totalPlays": 1547,
  "completedPlays": 1312,
  "completionRate": 84.8
}
```

**HTTP Status Codes:**
- 200 OK - Success with completion rate data
- 404 Not Found - Album doesn't exist

### Business Rules
1. completionRate = (completedPlays / totalPlays) * 100
2. Return 0.0 for completionRate if no plays exist (avoid division by zero)
3. Round completion rate to 1 decimal place
4. Count ALL plays (both completed and incomplete)
5. Return 404 if album doesn't exist

### Implementation Guide

**Controller Method:**
```java
@GetMapping("/{id}/completion-rate")
public ResponseEntity<Map<String, Object>> getCompletionRate(@PathVariable int id)
```

**Service Method:**
```java
public Map<String, Object> getCompletionRate(int albumId)
```
- Validate album exists (return null if not)
- Call DAO to get play counts
- Calculate completion rate percentage
- Round to 1 decimal place
- Build response map with all required fields

**DAO Method:**
```java
public Map<String, Object> getAlbumCompletionStats(int albumId)
```
- Query: JOIN albums with artists, LEFT JOIN with album_plays
- Count total plays and completed plays
- Return Map with album info and counts

### SQL Hints
```sql
-- Get completion statistics
SELECT 
    al.album_id,
    al.title,
    ar.name as artist_name,
    COUNT(ap.play_id) as total_plays,
    SUM(CASE WHEN ap.completed = true THEN 1 ELSE 0 END) as completed_plays
FROM albums al
JOIN artists ar ON al.artist_id = ar.artist_id
LEFT JOIN album_plays ap ON al.album_id = ap.album_id
WHERE al.album_id = ?
GROUP BY al.album_id, al.title, ar.name;
```

### Testing
**Test Cases:**
1. Album with 1000 total plays, 850 completed → Returns 85.0% completion rate
2. Album with no plays → Returns 0.0% completion rate
3. Album with 100% completion → Returns 100.0%
4. Non-existent album → Returns 404
5. Verify rounding: 847/1000 should return 84.7%

### Success Criteria
- ✅ Correctly calculates completion percentage
- ✅ Handles albums with zero plays (no division by zero error)
- ✅ Returns proper album information with artist name
- ✅ Rounds to 1 decimal place
- ✅ Returns 404 for non-existent albums

---

## **Feature #8: Artist Discovery Report**
**Difficulty:** Medium  
**Endpoint:** GET /api/artists/discovery?minPlays=10  
**Estimated Time:** 1-1.5 hours

### Business Need
Identify "hidden gems" - artists with high engagement but not well-known yet. These artists have fewer unique listeners but those listeners are highly engaged (many plays per listener). Perfect for playlist curation and artist promotion.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/artists/discovery?minPlays=10`
- Query Parameter: minPlays (integer, optional, default: 10)

**Response Format:**
```json
[
  {
    "artistId": 156,
    "name": "The Midnight Wanderers",
    "primaryGenre": "indie",
    "uniqueListeners": 23,
    "totalPlays": 487,
    "playsPerListener": 21.2
  },
  {
    "artistId": 89,
    "name": "Neon Dreams",
    "primaryGenre": "electronic",
    "uniqueListeners": 45,
    "totalPlays": 891,
    "playsPerListener": 19.8
  }
]
```

**HTTP Status Codes:**
- 200 OK - Success with discovery report (empty array if no qualifying artists)

### Business Rules
1. Only include artists with at least minPlays total plays
2. playsPerListener = totalPlays / uniqueListeners (round to 1 decimal)
3. Sort by playsPerListener DESC (highest engagement first)
4. Tie-breaker: artist name alphabetically (ASC)
5. Limit results to top 10 artists
6. minPlays constraints: minimum 1, maximum 100, default 10
7. Count ALL plays (completed and incomplete)

### Implementation Guide

**Controller Method:**
```java
@GetMapping("/discovery")
public ResponseEntity<List<Map<String, Object>>> getDiscoveryReport(
    @RequestParam(defaultValue = "10") int minPlays)
```

**Service Method:**
```java
public List<Map<String, Object>> getDiscoveryReport(int minPlays)
```
- Validate and cap minPlays (min 1, max 100)
- Call DAO to get artist stats
- Calculate playsPerListener for each artist in Java
- Round to 1 decimal place
- Return list

**DAO Method:**
```java
public List<Map<String, Object>> getArtistEngagementStats(int minPlays)
```
- Query: Complex JOIN through artists → albums → album_plays
- COUNT(DISTINCT user_id) for unique listeners
- COUNT(play_id) for total plays
- HAVING clause to filter by minPlays
- ORDER BY and LIMIT in SQL

### SQL Hints
```sql
-- Get artist engagement statistics
SELECT 
    ar.artist_id,
    ar.name,
    ar.primary_genre,
    COUNT(DISTINCT ap.user_id) as unique_listeners,
    COUNT(ap.play_id) as total_plays
FROM artists ar
JOIN albums al ON ar.artist_id = al.artist_id
JOIN album_plays ap ON al.album_id = ap.album_id
GROUP BY ar.artist_id, ar.name, ar.primary_genre
HAVING COUNT(ap.play_id) >= ?
ORDER BY (COUNT(ap.play_id) / COUNT(DISTINCT ap.user_id)) DESC, ar.name ASC
LIMIT 10;
```

**Note:** You may need to calculate playsPerListener in Java after fetching data, as some MySQL versions have issues with calculated columns in ORDER BY.

### Testing
**Test Cases:**
1. minPlays=10 → Returns artists with at least 10 plays
2. minPlays=1 → Returns more results (lower threshold)
3. minPlays=500 → Returns fewer results (higher threshold)
4. Verify playsPerListener calculation is correct
5. Verify sorting: highest playsPerListener first
6. Verify tie-breaker: alphabetical by name
7. Verify limit: maximum 10 results

### Success Criteria
- ✅ Correctly calculates unique listeners per artist
- ✅ Correctly calculates plays per listener ratio
- ✅ Respects minPlays threshold
- ✅ Returns top 10 artists only
- ✅ Proper sorting with tie-breaker

---

## **Feature #9: User Listening History by Month**
**Difficulty:** Medium  
**Endpoint:** GET /api/users/{userId}/history?year=2025&month=12  
**Estimated Time:** 1-1.5 hours

### Business Need
Let users see their listening activity for a specific month. Powers "Year in Review" and monthly recap features. Helps users track their music consumption patterns over time.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/users/{userId}/history?year=2025&month=12`
- Path Parameter: userId (integer)
- Query Parameters: 
  - year (integer, optional, defaults to current year)
  - month (integer, optional, defaults to current month)

**Response Format:**
```json
{
  "userId": 1,
  "username": "musiclover42",
  "year": 2025,
  "month": 12,
  "totalPlays": 247,
  "albums": [
    {
      "albumId": 42,
      "title": "Dark Side of the Moon",
      "artistName": "Pink Floyd",
      "playCount": 28
    },
    {
      "albumId": 15,
      "title": "Abbey Road",
      "artistName": "The Beatles",
      "playCount": 23
    }
  ]
}
```

**HTTP Status Codes:**
- 200 OK - Success with history data
- 404 Not Found - User doesn't exist
- 400 Bad Request - Invalid year or month parameter

### Business Rules
1. Year must be between 1900 and 2100
2. Month must be between 1 and 12
3. Default to current year/month if not provided
4. Return 404 if user doesn't exist
5. Return empty albums array if no plays that month
6. Count ALL plays (completed and incomplete)
7. Sort albums by playCount DESC, then title ASC
8. Include all albums played in that month (no limit)

### Implementation Guide

**Controller Method:**
```java
@GetMapping("/{id}/history")
public ResponseEntity<Map<String, Object>> getListeningHistory(
    @PathVariable int id,
    @RequestParam(required = false) Integer year,
    @RequestParam(required = false) Integer month)
```

**Service Method:**
```java
public Map<String, Object> getListeningHistory(int userId, Integer year, Integer month)
```
- Validate user exists (return null if not)
- Default year/month to current if not provided
- Validate year (1900-2100) and month (1-12)
- Throw IllegalArgumentException for invalid values
- Call DAO to get album history
- Calculate totalPlays by summing album play counts
- Build response map

**DAO Method:**
```java
public List<Map<String, Object>> getUserMonthlyAlbumHistory(int userId, int year, int month)
```
- Query: JOIN through album_plays → albums → artists
- WHERE clause: user_id, YEAR(played_at), MONTH(played_at)
- GROUP BY album
- ORDER BY play count DESC, title ASC

### SQL Hints
```sql
-- Get user's album plays for specific month
SELECT 
    al.album_id,
    al.title,
    ar.name as artist_name,
    COUNT(ap.play_id) as play_count
FROM album_plays ap
JOIN albums al ON ap.album_id = al.album_id
JOIN artists ar ON al.artist_id = ar.artist_id
WHERE ap.user_id = ?
  AND YEAR(ap.played_at) = ?
  AND MONTH(ap.played_at) = ?
GROUP BY al.album_id, al.title, ar.name
ORDER BY play_count DESC, al.title ASC;
```

### Testing
**Test Cases:**
1. User with plays in December 2025 → Returns correct album breakdown
2. User with no plays in specified month → Returns empty albums array, totalPlays: 0
3. No year/month params → Defaults to current month
4. Invalid year (1800) → Returns 400 Bad Request
5. Invalid month (13) → Returns 400 Bad Request
6. Non-existent user → Returns 404
7. Verify sorting: highest play count first, then alphabetical

### Success Criteria
- ✅ Correctly filters plays by year and month
- ✅ Defaults to current year/month when not specified
- ✅ Validates year and month parameters
- ✅ Returns 404 for non-existent users
- ✅ Returns 400 for invalid parameters
- ✅ Correctly groups and counts plays by album
- ✅ Proper sorting of results

---

## **Feature #10: Genre Diversity Score**
**Difficulty:** Hard/Stretch  
**Endpoint:** GET /api/users/{userId}/diversity-score  
**Estimated Time:** 2-3 hours

### Business Need
Gamification feature that encourages users to explore different genres. The diversity score shows how diverse their listening habits are. High scores indicate eclectic tastes, while low scores indicate genre-focused listening.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/users/{userId}/diversity-score`
- Path Parameter: userId (integer)

**Response Format:**
```json
{
  "userId": 1,
  "username": "musiclover42",
  "diversityScore": 73.5,
  "totalGenres": 8,
  "genreBreakdown": [
    {
      "genre": "rock",
      "playCount": 142,
      "percentage": 28.4
    },
    {
      "genre": "jazz",
      "playCount": 98,
      "percentage": 19.6
    },
    {
      "genre": "electronic",
      "playCount": 87,
      "percentage": 17.4
    }
  ]
}
```

**HTTP Status Codes:**
- 200 OK - Success with diversity score
- 404 Not Found - User doesn't exist

### Business Rules
1. Only count completed plays (completed = true)
2. Use Shannon Diversity Index formula (see below)
3. Score ranges from 0-100 (100 = perfectly diverse across all genres)
4. Return 404 if user doesn't exist
5. Return diversityScore: 0 if user has no plays
6. Sort genreBreakdown by playCount DESC
7. Round diversityScore to 1 decimal place
8. Round percentages to 1 decimal place

### Shannon Diversity Index Formula

The Shannon Diversity Index (H) measures how evenly distributed items are across categories:

**Step 1:** Calculate proportion for each genre
```
pi = (plays in genre i) / (total plays)
```

**Step 2:** Calculate Shannon Index
```
H = -Σ(pi × ln(pi))
```
Where Σ means "sum for all genres" and ln is the natural logarithm.

**Step 3:** Normalize to 0-100 scale
```
diversityScore = (H / ln(total_genres)) × 100
```

**Edge Cases:**
- If user has 0 plays: diversityScore = 0
- If user listened to only 1 genre: diversityScore = 0
- If perfectly balanced across N genres: diversityScore = 100

**Example Calculation:**
User listened to 3 genres:
- Rock: 50 plays (50%)
- Jazz: 30 plays (30%)
- Pop: 20 plays (20%)

```
p_rock = 0.50, p_jazz = 0.30, p_pop = 0.20
H = -(0.50 × ln(0.50) + 0.30 × ln(0.30) + 0.20 × ln(0.20))
H = -(0.50 × -0.693 + 0.30 × -1.204 + 0.20 × -1.609)
H = -(-0.347 + -0.361 + -0.322)
H = 1.030

diversityScore = (1.030 / ln(3)) × 100
diversityScore = (1.030 / 1.099) × 100
diversityScore = 93.7
```

### Implementation Guide

**Controller Method:**
```java
@GetMapping("/{id}/diversity-score")
public ResponseEntity<Map<String, Object>> getDiversityScore(@PathVariable int id)
```

**Service Method:**
```java
public Map<String, Object> getDiversityScore(int userId)
```
- Validate user exists (return null if not)
- Call DAO to get genre breakdown
- Calculate total plays
- Calculate diversity score using Shannon Index formula
- Calculate percentages for each genre
- Round all decimals to 1 place
- Build response map

**DAO Method:**
```java
public List<Map<String, Object>> getUserGenreBreakdown(int userId)
```
- Query: JOIN through album_plays → albums → artists
- WHERE: user_id and completed = true
- GROUP BY primary_genre
- ORDER BY play count DESC

### SQL Hints
```sql
-- Get user's genre breakdown
SELECT 
    ar.primary_genre as genre,
    COUNT(ap.play_id) as play_count
FROM album_plays ap
JOIN albums al ON ap.album_id = al.album_id
JOIN artists ar ON al.artist_id = ar.artist_id
WHERE ap.user_id = ? AND ap.completed = true
GROUP BY ar.primary_genre
ORDER BY play_count DESC;
```

### Java Implementation Hints

```java
// Calculate Shannon Index
double shannonIndex = 0.0;
int totalPlays = // sum of all play counts
int totalGenres = genreBreakdown.size();

for (Map<String, Object> genre : genreBreakdown) {
    int playCount = (int) genre.get("play_count");
    double proportion = (double) playCount / totalPlays;
    
    // Shannon formula: -Σ(pi × ln(pi))
    shannonIndex += proportion * Math.log(proportion);
}

shannonIndex = -shannonIndex; // Negate the sum

// Normalize to 0-100 scale
double diversityScore = 0.0;
if (totalGenres > 1) {
    double maxDiversity = Math.log(totalGenres);
    diversityScore = (shannonIndex / maxDiversity) * 100;
}

// Round to 1 decimal place
diversityScore = Math.round(diversityScore * 10.0) / 10.0;
```

### Testing
**Test Cases:**
1. User who only listens to one genre → Returns diversityScore: 0.0
2. User with perfectly balanced genres → Returns diversityScore: 100.0
3. User with no plays → Returns diversityScore: 0.0, empty genreBreakdown
4. User with multiple genres → Returns score between 0-100
5. Non-existent user → Returns 404
6. Verify Shannon Index calculation is correct
7. Verify percentages add up to 100% (allowing for rounding)

### Success Criteria
- ✅ Correctly implements Shannon Diversity Index
- ✅ Score ranges from 0-100
- ✅ Handles edge cases (0 plays, 1 genre)
- ✅ Returns detailed genre breakdown
- ✅ Proper sorting of genres by play count
- ✅ Returns 404 for non-existent users
- ✅ Only counts completed plays

---

## Implementation Strategy

### Recommended Order
1. **Feature #7** (Album Completion Rate) - Simplest, good warm-up
2. **Feature #6** (User Listening Streak) - Practice date/time logic
3. **Feature #9** (User Listening History) - Build on date filtering skills
4. **Feature #8** (Artist Discovery Report) - Practice complex aggregations
5. **Feature #10** (Genre Diversity Score) - Challenging math and algorithm

### Testing Approach
1. Write the DAO method first and test with manual SQL
2. Write the Service method and test business logic
3. Write the Controller method and test with Insomnia
4. Add comprehensive error handling
5. Test edge cases thoroughly

### Common Patterns
- Always validate user/album/artist exists before processing
- Use Map<String, Object> for flexible response structures
- Handle division by zero cases
- Round decimal values appropriately
- Return proper HTTP status codes
- Use meaningful error messages

Good luck implementing these bonus features!