---
name: "Bug #1: Missing @RestController Annotation"
about: Fix missing @RestController annotation on AlbumController
labels: bug, high-priority
title: "Bug #1: Missing @RestController Annotation"
---

## Bug #1: Missing @RestController Annotation

**Severity:** High  
**Component:** AlbumController  
**Priority:** Fix First

### Symptom
All album endpoints return 404 Not Found even though the controller class exists and has methods defined.

### Steps to Reproduce
1. Start the Spring Boot application
2. Make request to GET http://localhost:8080/api/albums
3. Observe 404 Not Found error
4. Try other album endpoints - all return 404

### Expected Behavior
- GET /api/albums should return 200 OK with list of albums
- All album endpoints should be accessible

### Actual Behavior
- All album endpoints return 404 Not Found
- Spring Boot doesn't recognize AlbumController as a REST controller

### Debugging Tips
- Check the class-level annotations on AlbumController.java
- Compare with other working controllers like ArtistController
- What annotation tells Spring "this class handles REST API requests"?
- Spring needs to know this class should handle HTTP requests

### Success Criteria
- GET /api/albums returns 200 OK with album data
- All album CRUD endpoints are accessible
- Insomnia tests for albums pass