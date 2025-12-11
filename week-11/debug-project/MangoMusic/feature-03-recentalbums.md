---
name: "Feature #3: Recent Album Releases"
about: Implement endpoint to get albums released in the last 2 years
labels: feature, medium
title: "Feature #3: Recent Album Releases"
---

## Feature #3: Recent Album Releases

**Priority:** Medium  
**Component:** Albums API  
**Requested By:** Discovery Team

### Business Need
The discovery team wants to showcase new music to users. They need an endpoint that returns recently released albums to feature in a "New Releases" section of the app.

### Feature Description
Create an endpoint that returns albums released in the last 2 years, ordered by release date (newest first). Support a limit parameter to control how many results are returned.

### Required Endpoint
- `GET /api/albums/recent?limit=10`

### Expected Response Format
```json
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
```

### Technical Requirements

**1. Controller Method:** Add to `AlbumController.java`:
```java
@GetMapping("/recent")
public ResponseEntity<List<Album>> getRecentAlbums(
@RequestParam(defaultValue = "10") int limit)
```

**2. Service Method:** Add to `AlbumService.java`:
- Method: `getRecentAlbums(int limit)`
- Should cap limit at 100 (don't allow unlimited results)
- Calculate "recent" as last 2 years from current year

**3. DAO Method:** Add to `AlbumDao.java`:
- Query to filter by release_year >= (YEAR(CURDATE()) - 2)
- ORDER BY release_year DESC, title ASC
- Apply LIMIT

### Business Rules
- "Recent" means released in the last 2 years (including current year)
- Default limit is 10
- Maximum limit is 100 (if user requests more, cap at 100)
- Order by release year descending (newest first), then by title alphabetically
- Include artist name in results

### Verification Steps
1. Test without limit parameter - should return 10 results
2. Test with limit=5 - should return 5 results
3. Test with limit=200 - should cap at 100 results
4. Verify all returned albums are from last 2 years

### Success Criteria
- Endpoint respects limit parameter
- Results are properly filtered and sorted
- Artist names are included
- Limit is capped at 100