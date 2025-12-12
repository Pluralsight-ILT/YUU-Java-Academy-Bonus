---
name: "Bug #5: Incorrect SQL Column Name in DAO"
about: Fix incorrect column name in AlbumDao getRecentAlbums method
labels: bug, high-priority
title: "Bug #5: Incorrect SQL Column Name in DAO"
---

## Bug #5: Incorrect SQL Column Name in DAO

**Severity:** High  
**Component:** AlbumDao - getRecentAlbums method  
**Priority:** Fix Fifth

### Symptom
When trying to fetch recent albums, the application throws SQLException with message about unknown column 'year' in field list or 'year' doesn't exist.

### Steps to Reproduce
1. Make GET request to http://localhost:8080/api/albums/recent?limit=5
2. Observe 500 Internal Server Error
3. Check logs for SQLException mentioning unknown column 'year'

### Expected Behavior
- GET /api/albums/recent should return 200 OK
- Should return list of albums released in the last 2 years

### Actual Behavior
- Returns 500 error
- SQLException: Unknown column 'year' in field list

### Debugging Tips
- Look at the SQL query in AlbumDao.getRecentAlbums()
- Check what columns are being selected or referenced in the query
- Look at the database schema or other working queries - what is the actual column name for the release year?
- Compare with other queries in AlbumDao that work correctly
- The error message tells you exactly what column name is wrong

### Success Criteria
- GET /api/albums/recent returns 200 OK
- Returns albums from the last 2 years
- Insomnia test "Albums - Get Recent Releases" passes