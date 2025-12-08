# MangoMusic API - Feature Implementation Tasks

These features extend the MangoMusic API with new endpoints. Implement them in order of priority, testing each one thoroughly before moving to the next.

---

## **Feature #1: Album Play Count**
**Priority:** Easy  
**Component:** Albums API  
**Requested By:** Analytics Team

**Business Need:**
The analytics team wants a simple way to check how many times a specific album has been played without pulling full play history. This will help quickly identify popular albums and track performance metrics.

**Feature Description:**
Create an endpoint that returns the total number of times an album has been played across all users and all time.

**Required Endpoint:**
- `GET /api/albums/{albumId}/play-count`

**Expected Response Format:**
~~~json
{
  "albumId": 1,
  "albumTitle": "Abbey Road",
  "artistName": "The Beatles",
  "playCount": 1245
}
~~~

**Technical Requirements:**

1. **Controller Method:** Add to `AlbumController.java`:
~~~java
@GetMapping("/{id}/play-count")
public ResponseEntity<Map<String, Object>> getPlayCount(@PathVariable int id)
~~~

2. **Service Method:** Add to `AlbumService.java`:
   - Method: `getAlbumPlayCount(int albumId)`
   - Should validate that album exists
   - Return a Map with albumId, albumTitle, artistName, and playCount

3. **DAO Method:** Add to `AlbumDao.java` or `AlbumPlayDao.java`:
   - Query to count plays for the album
   - JOIN with albums and artists tables to get names
   - Return the count

**Business Rules:**
- Return 404 if album doesn't exist
- Play count should include all plays across all users
- Count both completed and incomplete plays

**Verification Steps:**
1. Test with an album that has plays - verify count is accurate
2. Test with album ID 999 (doesn't exist) - should return 404
3. Compare result to manual query:
~~~sql
SELECT COUNT(*) FROM album_plays WHERE album_id = 1;
~~~

**Success Criteria:**
- Endpoint returns correct play count
- Album and artist names included in response
- Proper error handling for non-existent albums

---

## **Feature #2: Get Artist's Top Album**
**Priority:** Easy  
**Component:** Artists API  
**Requested By:** Content Team

**Business Need:**
The content team wants to quickly identify each artist's most popular album for promotional campaigns and featured content sections. This helps them make data-driven decisions about which albums to highlight.

**Feature Description:**
Create an endpoint that returns the most-played album for a given artist based on total play count across all users.

**Required Endpoint:**
- `GET /api/artists/{artistId}/top-album`

**Expected Response Format:**
~~~json
{
  "albumId": 42,
  "artistId": 1,
  "title": "Abbey Road",
  "releaseYear": 1969,
  "artistName": "The Beatles",
  "playCount": 1245
}
~~~

**Technical Requirements:**

1. **Controller Method:** Add to `ArtistController.java`:
~~~java
@GetMapping("/{id}/top-album")
public ResponseEntity<?> getTopAlbum(@PathVariable int id)
~~~

2. **Service Method:** Add to `ArtistService.java`:
   - Method: `getTopAlbumForArtist(int artistId)`
   - Should validate that artist exists
   - Return Album object (you may need to add a playCount field or return a Map)

3. **DAO Method:** Add to `AlbumDao.java`:
   - Query to find album with most plays for the artist
   - JOIN album_plays and albums tables
   - GROUP BY album, ORDER BY play count DESC, LIMIT 1

**Business Rules:**
- Return 404 if artist doesn't exist
- Return 404 if artist has no albums with plays
- If there's a tie, return the album with the lower album_id (most recent in our system)

**Verification Steps:**
1. Test with The Beatles (artist_id = 1) - should return their most played album
2. Test with an artist that has no plays - should return 404
3. Test with artist_id = 999 (doesn't exist) - should return 404

**Success Criteria:**
- Endpoint returns correct top album
- Play count is accurate
- Proper error handling

**Hints:**
- You'll need a query like: `SELECT al.*, COUNT(ap.play_id) as play_count FROM albums al JOIN album_plays ap ON ... WHERE al.artist_id = ? GROUP BY ... ORDER BY play_count DESC LIMIT 1`
- Consider whether you want to modify the Album model to include playCount or return a Map

---

## **Feature #3: Recent Album Releases**
**Priority:** Medium  
**Component:** Albums API  
**Requested By:** Discovery Team

**Business Need:**
The discovery team wants to showcase new music to users. They need an endpoint that returns recently released albums to feature in a "New Releases" section of the app.

**Feature Description:**
Create an endpoint that returns albums released in the last 2 years, ordered by release date (newest first). Support a limit parameter to control how many results are returned.

**Required Endpoint:**
- `GET /api/albums/recent?limit=10`

**Expected Response Format:**
~~~json
[
  {
    "albumId": 523,
    "artistId": 87,
    "title": "Modern Album",
    "releaseYear": 2024,
    "artistName": "Contemporary Artist"
  },
  {
    "albumId": 498,
    "artistId": 72,
    "title": "Last Year's Hit",
    "releaseYear": 2023,
    "artistName": "Another Artist"
  }
]
~~~

**Technical Requirements:**

1. **Controller Method:** Add to `AlbumController.java`:
~~~java
@GetMapping("/recent")
public ResponseEntity<List<Album>> getRecentAlbums(
    @RequestParam(defaultValue = "10") int limit)
~~~

2. **Service Method:** Add to `AlbumService.java`:
   - Method: `getRecentAlbums(int limit)`
   - Should cap limit at 100 (don't allow unlimited results)
   - Calculate "recent" as last 2 years from current year

3. **DAO Method:** Add to `AlbumDao.java`:
   - Query to filter by release_year >= (YEAR(CURDATE()) - 2)
   - ORDER BY release_year DESC, title ASC
   - Apply LIMIT

**Business Rules:**
- "Recent" means released in the last 2 years (including current year)
- Default limit is 10
- Maximum limit is 100 (if user requests more, cap at 100)
- Order by release year descending (newest first), then by title alphabetically
- Include artist name in results

**Verification Steps:**
1. Test without limit parameter - should return 10 results
2. Test with limit=5 - should return 5 results
3. Test with limit=200 - should cap at 100 results
4. Verify all returned albums are from last 2 years

**Success Criteria:**
- Endpoint respects limit parameter
- Results are properly filtered and sorted
- Artist names are included
- Limit is capped at 100

---

## **Feature #4: User's Favorite Genre**
**Priority:** Medium  
**Component:** Users API  
**Requested By:** Personalization Team

**Business Need:**
The personalization team wants to understand each user's music preferences to provide better recommendations. Knowing a user's favorite genre (based on listening history) is the first step in building a recommendation engine.

**Feature Description:**
Create an endpoint that returns the genre a user has listened to most based on their play history. This helps identify user preferences and can drive personalized content.

**Required Endpoint:**
- `GET /api/users/{userId}/favorite-genre`

**Expected Response Format:**
~~~json
{
  "userId": 1,
  "username": "john_doe",
  "favoriteGenre": "rock",
  "playsInGenre": 847
}
~~~

**Technical Requirements:**

1. **Controller Method:** Add to `UserController.java`:
~~~java
@GetMapping("/{id}/favorite-genre")
public ResponseEntity<?> getFavoriteGenre(@PathVariable int id)
~~~

2. **Service Method:** Add to `UserService.java`:
   - Method: `getFavoriteGenre(int userId)`
   - Should validate that user exists
   - Return a Map with userId, username, favoriteGenre, and playsInGenre

3. **DAO Method:** Add to `UserDao.java`:
   - Complex query joining: album_plays → albums → artists
   - GROUP BY genre
   - ORDER BY play count DESC
   - LIMIT 1
   - Also need to fetch the user's username

**Business Rules:**
- Return 404 if user doesn't exist
- Return 404 if user has no play history
- Count both completed and incomplete plays
- If there's a tie, return the genre that comes first alphabetically

**Verification Steps:**
1. Test with user ID 1 - should return their most-played genre
2. Test with a user that has no plays - should return 404
3. Test with user ID 999 (doesn't exist) - should return 404
4. Verify the play count matches a manual query

**Success Criteria:**
- Endpoint returns correct favorite genre
- Play count is accurate
- Username is included in response
- Proper error handling

**Hints:**
- Your query needs to join three tables: `album_plays -> albums -> artists`
- Use `GROUP BY artists.primary_genre`
- You might want to use a subquery or create a helper method to get the username separately

---

## **Feature #5: Trending Albums**
**Priority:** Hard (Stretch Goal)  
**Component:** Albums API  
**Requested By:** Marketing Team

**Business Need:**
The marketing team wants to promote "trending" albums - those that are gaining popularity right now. They need an endpoint that shows the most-played albums within a recent time window (last 7 days by default) to create dynamic trending charts.

**Feature Description:**
Create an endpoint that returns the top albums by play count within a specified number of days. This creates a "what's hot right now" feature that updates based on recent listening activity.

**Required Endpoint:**
- `GET /api/albums/trending?days=7`

**Expected Response Format:**
~~~json
[
  {
    "albumId": 42,
    "artistId": 1,
    "title": "Abbey Road",
    "releaseYear": 1969,
    "artistName": "The Beatles",
    "recentPlayCount": 287,
    "trendingRank": 1
  },
  {
    "albumId": 156,
    "artistId": 23,
    "title": "Back in Black",
    "releaseYear": 1980,
    "artistName": "AC/DC",
    "recentPlayCount": 251,
    "trendingRank": 2
  }
]
~~~

**Technical Requirements:**

1. **Controller Method:** Add to `AlbumController.java`:
~~~java
@GetMapping("/trending")
public ResponseEntity<List<Map<String, Object>>> getTrendingAlbums(
    @RequestParam(defaultValue = "7") int days)
~~~

2. **Service Method:** Add to `AlbumService.java`:
   - Method: `getTrendingAlbums(int days)`
   - Should cap days at 30 (don't allow queries too far back)
   - Minimum days should be 1
   - Return top 10 albums

3. **DAO Method:** Add to `AlbumDao.java`:
   - Query to filter plays by date: `WHERE played_at >= DATE_SUB(CURDATE(), INTERVAL ? DAY)`
   - JOIN album_plays → albums → artists
   - GROUP BY album
   - ORDER BY play count DESC
   - LIMIT 10
   - Include a ranking number (1, 2, 3, etc.)

**Business Rules:**
- Default to last 7 days if days parameter not provided
- Minimum days = 1, maximum days = 30
- Always return top 10 albums only
- If fewer than 10 albums have plays in the timeframe, return all available
- Include trending rank (1 for most played, 2 for second, etc.)
- Order by play count descending

**Verification Steps:**
1. Test without days parameter - should use 7 days
2. Test with days=1 - should only count today's plays
3. Test with days=50 - should cap at 30 days
4. Test with days=0 or negative - should use minimum of 1
5. Verify play counts are accurate for the time period

**Success Criteria:**
- Endpoint returns top 10 trending albums
- Date filtering works correctly
- Parameter validation (min/max days)
- Ranking is accurate
- Recent play count only includes plays within the specified time window

**Hints:**
- Use `DATE_SUB(CURDATE(), INTERVAL ? DAY)` for date filtering
- You can add ranking in the query or in Java code after fetching results
- Consider creating a helper class or using `Map<String, Object>` to return the extra fields (recentPlayCount, trendingRank)
- Test your date math carefully - "last 7 days" typically means including today

---

## General Implementation Tips

1. **Start with the DAO layer** - Write and test your SQL queries in MySQL Workbench first
2. **Build up through the layers** - DAO → Service → Controller
3. **Test as you go** - Use Insomnia to test each endpoint as you complete it
4. **Handle errors properly** - Return appropriate HTTP status codes (404, 400, 200)
5. **Follow existing patterns** - Look at how similar endpoints are implemented in the codebase

## Testing Your Features

Use Insomnia to test each endpoint:
- Test happy path (valid data)
- Test error cases (invalid IDs, missing data)
- Verify response format matches expected structure
- Check that data is accurate by comparing to manual SQL queries