---
name: "Feature #7: Album Completion Rate"
about: Calculate and return album completion rate statistics
labels: feature, easy
title: "Feature #7: Album Completion Rate"
---

## Feature #7: Album Completion Rate

**Difficulty:** Easy  
**Endpoint:** GET /api/albums/{albumId}/completion-rate  
**Estimated Time:** 30-45 minutes

### Business Need
Help artists and labels understand listener engagement. High completion rates indicate engaging content, while low rates might suggest issues with album quality or length.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/albums/{albumId}/completion-rate`
- Path Parameter: albumId (integer)

**Response Format:**
```json
{
  "albumId": 42,
  "title": "Dark Side of the Moon",
  "artistName": "Pink Floyd",
  "totalPlays": 1547,
  "completedPlays": 1312,
  "completionRate": 84.8
}
```

### Business Rules
- ✅ completionRate = (completedPlays / totalPlays) × 100
- ✅ Return 0.0 for completionRate if no plays exist (avoid division by zero)
- ✅ Round completion rate to 1 decimal place
- ✅ Count ALL plays (both completed and incomplete)
- ✅ Return 404 if album doesn't exist

### Implementation Checklist
- [ ] Create `AlbumController.getCompletionRate()` method
- [ ] Create `AlbumService.getCompletionRate()` method
- [ ] Create `AlbumDao.getAlbumCompletionStats()` method
- [ ] Implement completion rate calculation
- [ ] Handle division by zero (no plays)
- [ ] Add proper error handling (404 for non-existent album)
- [ ] Test with Insomnia
- [ ] Verify rounding to 1 decimal place

### Testing Checklist
- [ ] Album with plays returns correct completion percentage
- [ ] Album with no plays returns 0.0% (no error)
- [ ] Album with 100% completion returns 100.0%
- [ ] Non-existent album returns 404
- [ ] Rounding works correctly (e.g., 847/1000 = 84.7%)

### Related Files
- `src/main/java/com/mangomusic/controller/AlbumController.java`
- `src/main/java/com/mangomusic/service/AlbumService.java`
- `src/main/java/com/mangomusic/dao/AlbumDao.java`

### SQL Hint
Use `SUM(CASE WHEN ap.completed = true THEN 1 ELSE 0 END)` to count completed plays.

### Reference
See `feature_tickets-bonus.md` for complete implementation details.