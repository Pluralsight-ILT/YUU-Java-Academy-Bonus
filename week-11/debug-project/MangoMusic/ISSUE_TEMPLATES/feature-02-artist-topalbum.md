---
name: "Feature #2: Get Artist's Top Album"
about: Implement endpoint to get the most-played album for an artist
labels: feature, easy
title: "Feature #2: Get Artist's Top Album"
---

## Feature #2: Get Artist's Top Album

**Priority:** Easy  
**Component:** Artists API  
**Requested By:** Content Team

### Business Need
The content team wants to quickly identify each artist's most popular album for promotional campaigns and featured content sections. This helps them make data-driven decisions about which albums to highlight.

### Feature Description
Create an endpoint that returns the most-played album for a given artist based on total play count across all users.

### Required Endpoint
- `GET /api/artists/{artistId}/top-album`

### Expected Response Format
```json
{
  "albumId": 42,
  "artistId": 1,
  "title": "Abbey Road",
  "releaseYear": 1969,
  "artistName": "The Beatles",
  "playCount": 1245
}
```

### Technical Requirements

**1. Controller Method:** Add to `ArtistController.java`:
```java
@GetMapping("/{id}/top-album")
public ResponseEntity<?> getTopAlbum(@PathVariable int id)
```

**2. Service Method:** Add to `ArtistService.java`:
- Method: `getTopAlbumForArtist(int artistId)`
- Should validate that artist exists
- Return Album object (you may need to add a playCount field or return a Map)

**3. DAO Method:** Add to `AlbumDao.java`:
- Query to find album with most plays for the artist
- JOIN album_plays and albums tables
- GROUP BY album, ORDER BY play count DESC, LIMIT 1

### Business Rules
- Return 404 if artist doesn't exist
- Return 404 if artist has no albums with plays
- If there's a tie, return the album with the lower album_id (most recent in our system)

### Verification Steps
1. Test with The Beatles (artist_id = 1) - should return their most played album
2. Test with an artist that has no plays - should return 404
3. Test with artist_id = 999 (doesn't exist) - should return 404

### Success Criteria
- Endpoint returns correct top album
- Play count is accurate
- Proper error handling

### Hints
- You'll need a query like: `SELECT al.*, COUNT(ap.play_id) as play_count FROM albums al JOIN album_plays ap ON ... WHERE al.artist_id = ? GROUP BY ... ORDER BY play_count DESC LIMIT 1`
- Consider whether you want to modify the Album model to include playCount or return a Map