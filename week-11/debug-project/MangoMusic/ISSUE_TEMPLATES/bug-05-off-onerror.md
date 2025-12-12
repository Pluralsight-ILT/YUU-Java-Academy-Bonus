---
name: "Bug #7: Off-by-One Error in Pagination Logic"
about: Fix off-by-one error in AlbumPlayService getUserRecentPlays method
labels: bug, low-priority
title: "Bug #7: Off-by-One Error in Pagination Logic"
---

## Bug #7: Off-by-One Error in Pagination Logic

**Severity:** Low  
**Component:** AlbumPlayService - getUserRecentPlays method  
**Priority:** Fix Seventh

### Symptom
When requesting a user's recent plays with a limit parameter, the API consistently returns one fewer result than requested. For example, requesting limit=20 returns only 19 plays, limit=5 returns only 4 plays.

### Steps to Reproduce
1. Make GET request to http://localhost:8080/api/plays/user/1?limit=20
2. Count the number of plays returned
3. Observe only 19 plays returned instead of 20
4. Try with limit=5, observe only 4 plays returned

### Expected Behavior
- GET /api/plays/user/1?limit=20 should return exactly 20 plays (or fewer if user has fewer than 20 total plays)
- The limit parameter should be honored exactly

### Actual Behavior
- Returns limit - 1 results
- Always one fewer than requested

### Debugging Tips
- Look at the validation logic in AlbumPlayService.getUserRecentPlays()
- Is the limit being adjusted or modified anywhere?
- Trace through the logic: if limit comes in as 20, what value gets passed to the DAO?
- Check for any arithmetic operations on the limit value
- Classic off-by-one error - something is subtracting when it shouldn't be

### Success Criteria
- GET /api/plays/user/1?limit=20 returns exactly 20 plays (if user has at least 20)
- The limit parameter is honored correctly
- Insomnia test "Plays - Get User Recent Plays" passes