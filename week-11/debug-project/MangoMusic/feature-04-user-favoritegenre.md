---
name: "Feature #4: User's Favorite Genre"
about: Implement endpoint to get a user's most-played genre
labels: feature, medium
title: "Feature #4: User's Favorite Genre"
---

## Feature #4: User's Favorite Genre

**Priority:** Medium  
**Component:** Users API  
**Requested By:** Personalization Team

### Business Need
The personalization team wants to understand each user's music preferences to provide better recommendations. Knowing a user's favorite genre (based on listening history) is the first step in building a recommendation engine.

### Feature Description
Create an endpoint that returns the genre a user has listened to most based on their play history. This helps identify user preferences and can drive personalized content.

### Required Endpoint
- `GET /api/users/{userId}/favorite-genre`

### Expected Response Format
```json
{
  "userId": 1,
  "username": "john_doe",
  "favoriteGenre": "rock",
  "playsInGenre": 847
}
```

### Technical Requirements

**1. Controller Method:** Add to `UserController.java`:
```java
@GetMapping("/{id}/favorite-genre")
public ResponseEntity<?> getFavoriteGenre(@PathVariable int id)
```

**2. Service Method:** Add to `UserService.java`:
- Method: `getFavoriteGenre(int userId)`
- Should validate that user exists
- Return a Map with userId, username, favoriteGenre, and playsInGenre

**3. DAO Method:** Add to `UserDao.java`:
- Complex query joining: album_plays → albums → artists
- GROUP BY genre
- ORDER BY play count DESC
- LIMIT 1
- Also need to fetch the user's username

### Business Rules
- Return 404 if user doesn't exist
- Return 404 if user has no play history
- Count both completed and incomplete plays
- If there's a tie, return the genre that comes first alphabetically

### Verification Steps
1. Test with user ID 1 - should return their most-played genre
2. Test with a user that has no plays - should return 404
3. Test with user ID 999 (doesn't exist) - should return 404
4. Verify the play count matches a manual query

### Success Criteria
- Endpoint returns correct favorite genre
- Play count is accurate
- Username is included in response
- Proper error handling

### Hints
- Your query needs to join three tables: `album_plays -> albums -> artists`
- Use `GROUP BY artists.primary_genre`
- You might want to use a subquery or create a helper method to get the username separately