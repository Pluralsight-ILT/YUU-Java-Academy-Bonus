---
name: "Feature #1: Album Play Count"
about: Implement endpoint to get total play count for an album
labels: feature, easy
title: "Feature #1: Album Play Count"
---

## Feature #1: Album Play Count

**Priority:** Easy  
**Component:** Albums API  
**Requested By:** Analytics Team

### Business Need
The analytics team wants a simple way to check how many times a specific album has been played without pulling full play history. This will help quickly identify popular albums and track performance metrics.

### Feature Description
Create an endpoint that returns the total number of times an album has been played across all users and all time.

### Required Endpoint
- `GET /api/albums/{albumId}/play-count`

### Expected Response Format
```json
{
  "albumId": 1,
  "albumTitle": "Abbey Road",
  "artistName": "The Beatles",
  "playCount": 1245
}
```

### Technical Requirements

**1. Controller Method:** Add to `AlbumController.java`:
```java
@GetMapping("/{id}/play-count")
public ResponseEntity<Map<String, Object>> getPlayCount(@PathVariable int id)
```

**2. Service Method:** Add to `AlbumService.java`:
- Method: `getAlbumPlayCount(int albumId)`
- Should validate that album exists
- Return a Map with albumId, albumTitle, artistName, and playCount

**3. DAO Method:** Add to `AlbumDao.java` or `AlbumPlayDao.java`:
- Query to count plays for the album
- JOIN with albums and artists tables to get names
- Return the count

### Business Rules
- Return 404 if album doesn't exist
- Play count should include all plays across all users
- Count both completed and incomplete plays

### Verification Steps
1. Test with an album that has plays - verify count is accurate
2. Test with album ID 999 (doesn't exist) - should return 404
3. Compare result to manual query:
```sql
SELECT COUNT(*) FROM album_plays WHERE album_id = 1;
```

### Success Criteria
- Endpoint returns correct play count
- Album and artist names included in response
- Proper error handling for non-existent albums