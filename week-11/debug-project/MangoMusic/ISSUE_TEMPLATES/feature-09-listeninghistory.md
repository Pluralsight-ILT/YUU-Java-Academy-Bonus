---
name: "Feature #9: User Listening History by Month"
about: Show user's listening activity breakdown for a specific month
labels: feature, medium
title: "Feature #9: User Listening History by Month"
---

## Feature #9: User Listening History by Month

**Difficulty:** Medium  
**Endpoint:** GET /api/users/{userId}/history?year=2025&month=12  
**Estimated Time:** 1-1.5 hours

### Business Need
Let users see their listening activity for a specific month. Powers "Year in Review" and monthly recap features. Helps users track their music consumption patterns over time.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/users/{userId}/history?year=2025&month=12`
- Path Parameter: userId (integer)
- Query Parameters: 
  - year (integer, optional, defaults to current year)
  - month (integer, optional, defaults to current month)

**Response Format:**
```json
{
  "userId": 1,
  "username": "musiclover42",
  "year": 2025,
  "month": 12,
  "totalPlays": 247,
  "albums": [
    {
      "albumId": 42,
      "title": "Dark Side of the Moon",
      "artistName": "Pink Floyd",
      "playCount": 28
    }
  ]
}
```

### Business Rules
- ✅ Year must be between 1900 and 2100
- ✅ Month must be between 1 and 12
- ✅ Default to current year/month if not provided
- ✅ Return 404 if user doesn't exist
- ✅ Return empty albums array if no plays that month
- ✅ Sort albums by playCount DESC, then title ASC

### Implementation Checklist
- [ ] Create `UserController.getListeningHistory()` method with @RequestParam
- [ ] Create `UserService.getListeningHistory()` method
- [ ] Default year/month to current values if not provided
- [ ] Validate year (1900-2100) and month (1-12)
- [ ] Throw IllegalArgumentException for invalid parameters
- [ ] Create `UserDao.getUserMonthlyAlbumHistory()` method
- [ ] Implement WHERE clause with YEAR() and MONTH() functions
- [ ] Calculate totalPlays from album breakdown
- [ ] Add proper error handling (404 for non-existent user, 400 for invalid params)
- [ ] Test with Insomnia
- [ ] Verify default behavior and validation

### Testing Checklist
- [ ] User with plays in December 2025 returns correct breakdown
- [ ] User with no plays in specified month returns empty albums array
- [ ] No year/month params defaults to current month
- [ ] Invalid year (1800) returns 400 Bad Request
- [ ] Invalid month (13) returns 400 Bad Request
- [ ] Non-existent user returns 404
- [ ] Sorting works: highest play count first, then alphabetical

### Related Files
- `src/main/java/com/mangomusic/controller/UserController.java`
- `src/main/java/com/mangomusic/service/UserService.java`
- `src/main/java/com/mangomusic/dao/UserDao.java`

### SQL Hints
- Use `YEAR(ap.played_at)` and `MONTH(ap.played_at)` in WHERE clause
- GROUP BY album to get play counts per album
- ORDER BY play_count DESC, then title ASC

### Reference
See `feature_tickets-bonus.md` for complete implementation details.