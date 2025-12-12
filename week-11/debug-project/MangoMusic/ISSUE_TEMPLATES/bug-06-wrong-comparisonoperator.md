---
name: "Bug #6: Wrong Comparison Operator in Service Validation"
about: Fix incorrect comparison operator in AlbumService getRecentAlbums validation
labels: bug, medium-priority
title: "Bug #6: Wrong Comparison Operator in Service Validation"
---

## Bug #6: Wrong Comparison Operator in Service Validation

**Severity:** Medium  
**Component:** AlbumService - getRecentAlbums method  
**Priority:** Fix Sixth

### Symptom
When requesting recent albums with a reasonable limit parameter (like 10 or 50), the endpoint returns 200 OK but with an empty array, even though recent albums exist in the database. If you pass limit=200, it suddenly works and returns albums.

### Steps to Reproduce
1. Make GET request to http://localhost:8080/api/albums/recent?limit=10
2. Observe 200 OK but empty array []
3. Make GET request to http://localhost:8080/api/albums/recent?limit=200
4. Observe 200 OK with albums returned (unexpected!)

### Expected Behavior
- Should return albums for any reasonable limit value between 1 and 100
- limit=10 should work perfectly fine
- limit=200 should be capped at 100

### Actual Behavior
- Small valid limits (1-99) return empty arrays
- Large limits (100+) work but shouldn't
- The validation logic is backwards

### Debugging Tips
- Look at the validation logic in AlbumService.getRecentAlbums()
- What happens when limit is less than 100? What should happen?
- What happens when limit is greater than 100? What should happen?
- Compare with AlbumPlayService.getUserRecentPlays() - how does it validate the limit?
- Think about the logic: "If limit is greater than 100, cap it at 100"
- The behavior is opposite of what it should be

### Success Criteria
- GET /api/albums/recent?limit=5 returns 5 albums (or fewer if less than 5 exist)
- GET /api/albums/recent?limit=200 returns maximum of 100 albums
- Insomnia test "Albums - Get Recent Releases" passes