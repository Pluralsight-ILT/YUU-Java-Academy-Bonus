---
name: "Bug #3: Missing @PathVariable Annotation"
about: Fix missing @PathVariable annotation on ArtistController getArtistById method
labels: bug, high-priority
title: "Bug #3: Missing @PathVariable Annotation"
---

## Bug #3: Missing @PathVariable Annotation

**Severity:** High  
**Component:** ArtistController - getArtistById method  
**Priority:** Fix Third

### Symptom
When trying to get a specific artist by ID, the server returns 500 Internal Server Error with a message about parameter binding failure or missing required parameter.

### Steps to Reproduce
1. Make GET request to http://localhost:8080/api/artists/1
2. Observe 500 Internal Server Error
3. Check logs for binding/parameter errors

### Expected Behavior
- GET /api/artists/1 should return 200 OK with artist data for ID 1
- The {id} from the URL path should bind to the method parameter

### Actual Behavior
- Returns 500 error
- Spring cannot bind the URL path parameter to the method parameter

### Debugging Tips
- Look at the getArtistById method signature in ArtistController.java
- The method has `int id` as a parameter, but how does Spring know where to get this value?
- Compare with getAlbumById in AlbumController - what annotation does it have on its parameter?
- What annotation tells Spring "get this value from the URL path"?
- The URL has {id} but the parameter doesn't know it should come from there

### Success Criteria
- GET /api/artists/1 returns 200 OK with artist data
- Artist ID correctly maps from URL to method parameter
- Insomnia test "Artists - Get by ID" passes