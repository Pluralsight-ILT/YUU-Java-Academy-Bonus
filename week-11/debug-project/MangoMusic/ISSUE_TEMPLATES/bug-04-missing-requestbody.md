---
name: "Bug #4: Missing @RequestBody Annotation"
about: Fix missing @RequestBody annotation on AlbumPlayController createPlay method
labels: bug, high-priority
title: "Bug #4: Missing @RequestBody Annotation"
---

## Bug #4: Missing @RequestBody Annotation

**Severity:** High  
**Component:** AlbumPlayController - createPlay method  
**Priority:** Fix Fourth

### Symptom
When attempting to create a new play via POST request with valid JSON in the request body, the server returns 400 Bad Request or the service layer receives a null object causing NullPointerException.

### Steps to Reproduce
1. Make POST request to http://localhost:8080/api/plays
2. Include valid JSON body:
```json
{
  "userId": 1,
  "albumId": 1,
  "playedAt": "2025-12-05T10:30:00",
  "completed": true
}
```
3. Observe 400 Bad Request or 500 error with NullPointerException

### Expected Behavior
- POST /api/plays should return 201 Created
- Should return the created play object with a generated playId

### Actual Behavior
- Returns 400 Bad Request or 500 error
- The AlbumPlay object parameter in the method is null
- Request body is not being deserialized into the Java object

### Debugging Tips
- Look at the createPlay method signature in AlbumPlayController.java
- The method has `AlbumPlay play` as a parameter, but Spring doesn't know this should come from the request body
- Compare with createArtist in ArtistController - what annotation does its parameter have?
- What annotation tells Spring "deserialize the JSON body into this object"?
- Without this annotation, Spring doesn't know the JSON should map to the parameter

### Success Criteria
- POST /api/plays returns 201 Created
- Returns play object with generated playId
- Insomnia test "Plays - Create (Log Play)" passes