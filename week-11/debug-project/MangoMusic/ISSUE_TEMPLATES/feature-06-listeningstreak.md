---
name: "Feature #6: User Listening Streak"
about: Implement listening streak tracking for users
labels: feature, easy
title: "Feature #6: User Listening Streak"
---

## Feature #6: User Listening Streak

**Difficulty:** Easy  
**Endpoint:** GET /api/users/{userId}/listening-streak  
**Estimated Time:** 30-45 minutes

### Business Need
Track user engagement by showing their current listening streak (consecutive days with at least one play). This gamification feature encourages daily usage and helps identify highly engaged users.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/users/{userId}/listening-streak`
- Path Parameter: userId (integer)

**Response Format:**
```json
{
  "userId": 1,
  "username": "musiclover42",
  "currentStreak": 5,
  "longestStreak": 12,
  "lastPlayDate": "2025-12-10"
}
```

### Business Rules
- ✅ Streak breaks if user misses a full day (no plays between 00:00:00 and 23:59:59)
- ✅ Only count completed plays (completed = true)
- ✅ Today counts toward current streak if user has played today
- ✅ Return 404 if user doesn't exist
- ✅ Return currentStreak: 0 and longestStreak: 0 if user has no plays

### Implementation Checklist
- [ ] Create `UserController.getListeningStreak()` method
- [ ] Create `UserService.getListeningStreak()` method
- [ ] Create `UserDao.getUserPlayDates()` method
- [ ] Implement current streak calculation algorithm
- [ ] Implement longest streak calculation algorithm
- [ ] Add proper error handling (404 for non-existent user)
- [ ] Test with Insomnia
- [ ] Verify edge cases (no plays, played yesterday but not today)

### Testing Checklist
- [ ] User with 5-day current streak returns currentStreak: 5
- [ ] User who played yesterday but not today returns currentStreak: 0
- [ ] User with no plays returns currentStreak: 0, longestStreak: 0
- [ ] Non-existent user returns 404
- [ ] Only completed plays are counted

### Related Files
- `src/main/java/com/mangomusic/controller/UserController.java`
- `src/main/java/com/mangomusic/service/UserService.java`
- `src/main/java/com/mangomusic/dao/UserDao.java`

### Reference
See `feature_tickets-bonus.md` for complete implementation details.