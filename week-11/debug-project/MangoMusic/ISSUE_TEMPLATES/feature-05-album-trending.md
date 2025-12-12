---
name: "Feature #5: Trending Albums"
about: Implement endpoint to get trending albums based on recent play activity
labels: feature, hard, stretch-goal
title: "Feature #5: Trending Albums"
---

## Feature #5: Trending Albums

**Priority:** Hard (Stretch Goal)  
**Component:** Albums API  
**Requested By:** Marketing Team

### Business Need
The marketing team wants to promote "trending" albums - those that are gaining popularity right now. They need an endpoint that shows the most-played albums within a recent time window (last 7 days by default) to create dynamic trending charts.

### Feature Description
Create an endpoint that returns the top albums by play count within a specified number of days. This creates a "what's hot right now" feature that updates based on recent listening activity.

### Required Endpoint
- `GET /api/albums/trending?days=7`

### Expected Response Format
```json
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
```

### Technical Requirements

**1. Controller Method:** Add to `AlbumController.java`:
```java
@GetMapping("/trending")
public ResponseEntity<List<Map<String, Object>>> getTrendingAlbums(
    @RequestParam(defaultValue = "7") int days)
```

**2. Service Method:** Add to `AlbumService.java`:
- Method: `getTrendingAlbums(int days)`
- Should cap days at 30 (don't allow queries too far back)
- Minimum days should be 1
- Return top 10 albums

**3. DAO Method:** Add to `AlbumDao.java`:
- Query to filter plays by date: `WHERE played_at >= DATE_SUB(CURDATE(), INTERVAL ? DAY)`
- JOIN album_plays → albums → artists
- GROUP BY album
- ORDER BY play count DESC
- LIMIT 10
- Include a ranking number (1, 2, 3, etc.)

### Business Rules
- Default to last 7 days if days parameter not provided
- Minimum days = 1, maximum days = 30
- Always return top 10 albums only
- If fewer than 10 albums have plays in the timeframe, return all available
- Include trending rank (1 for most played, 2 for second, etc.)
- Order by play count descending

### Verification Steps
1. Test without days parameter - should use 7 days
2. Test with days=1 - should only count today's plays
3. Test with days=50 - should cap at 30 days
4. Test with days=0 or negative - should use minimum of 1
5. Verify play counts are accurate for the time period

### Success Criteria
- Endpoint returns top 10 trending albums
- Date filtering works correctly
- Parameter validation (min/max days)
- Ranking is accurate
- Recent play count only includes plays within the specified time window

### Hints
- Use `DATE_SUB(CURDATE(), INTERVAL ? DAY)` for date filtering
- You can add ranking in the query or in Java code after fetching results
- Consider creating a helper class or using `Map<String, Object>` to return the extra fields (recentPlayCount, trendingRank)
- Test your date math carefully - "last 7 days" typically means including today