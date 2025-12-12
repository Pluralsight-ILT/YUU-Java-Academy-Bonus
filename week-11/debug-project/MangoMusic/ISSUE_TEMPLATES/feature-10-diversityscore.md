---
name: "Feature #10: Genre Diversity Score"
about: Calculate Shannon Diversity Index for user's genre listening patterns
labels: feature, hard, stretch-goal
title: "Feature #10: Genre Diversity Score"
---

## Feature #10: Genre Diversity Score

**Difficulty:** Hard/Stretch  
**Endpoint:** GET /api/users/{userId}/diversity-score  
**Estimated Time:** 2-3 hours

### Business Need
Gamification feature that encourages users to explore different genres. The diversity score shows how diverse their listening habits are using the Shannon Diversity Index. High scores indicate eclectic tastes, while low scores indicate genre-focused listening.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/users/{userId}/diversity-score`
- Path Parameter: userId (integer)

**Response Format:**
```json
{
  "userId": 1,
  "username": "musiclover42",
  "diversityScore": 73.5,
  "totalGenres": 8,
  "genreBreakdown": [
    {
      "genre": "rock",
      "playCount": 142,
      "percentage": 28.4
    }
  ]
}
```

### Business Rules
- ✅ Only count completed plays (completed = true)
- ✅ Use Shannon Diversity Index formula
- ✅ Score ranges from 0-100 (100 = perfectly diverse)
- ✅ Return diversityScore: 0 if user has no plays
- ✅ Sort genreBreakdown by playCount DESC
- ✅ Round diversityScore and percentages to 1 decimal place

### Shannon Diversity Index Formula
```
Step 1: Calculate proportion for each genre
pi = (plays in genre i) / (total plays)

Step 2: Calculate Shannon Index
H = -Σ(pi × ln(pi))

Step 3: Normalize to 0-100 scale
diversityScore = (H / ln(total_genres)) × 100

Edge Cases:
- If 0 plays: diversityScore = 0
- If 1 genre: diversityScore = 0
- If perfectly balanced: diversityScore = 100
```

### Implementation Checklist
- [ ] Create `UserController.getDiversityScore()` method
- [ ] Create `UserService.getDiversityScore()` method
- [ ] Create `UserDao.getUserGenreBreakdown()` method
- [ ] Implement genre breakdown query (only completed plays)
- [ ] Calculate total plays from genre breakdown
- [ ] Implement Shannon Diversity Index calculation in Java
- [ ] Calculate proportion (pi) for each genre
- [ ] Calculate H = -Σ(pi × ln(pi)) using Math.log()
- [ ] Normalize to 0-100 scale
- [ ] Calculate percentage for each genre
- [ ] Round all decimals to 1 place
- [ ] Handle edge cases (0 plays, 1 genre)
- [ ] Add proper error handling (404 for non-existent user)
- [ ] Test with Insomnia
- [ ] Verify mathematical accuracy

### Testing Checklist
- [ ] User who only listens to one genre returns diversityScore: 0.0
- [ ] User with perfectly balanced genres returns diversityScore: 100.0
- [ ] User with no plays returns diversityScore: 0.0, empty genreBreakdown
- [ ] User with multiple genres returns score between 0-100
- [ ] Non-existent user returns 404
- [ ] Shannon Index calculation is mathematically correct
- [ ] Percentages add up to 100% (allowing for rounding)
- [ ] genreBreakdown sorted by playCount DESC

### Related Files
- `src/main/java/com/mangomusic/controller/UserController.java`
- `src/main/java/com/mangomusic/service/UserService.java`
- `src/main/java/com/mangomusic/dao/UserDao.java`

### Java Implementation Hints
```java
// In Service layer
double shannonIndex = 0.0;
for (genre : genreBreakdown) {
    double proportion = playCount / totalPlays;
    shannonIndex += proportion * Math.log(proportion);
}
shannonIndex = -shannonIndex;

// Normalize
double maxDiversity = Math.log(totalGenres);
double diversityScore = (shannonIndex / maxDiversity) * 100;
```

### Reference
See `feature_tickets-bonus.md` for complete implementation details including full example calculation.

### Additional Resources
- [Shannon Diversity Index on Wikipedia](https://en.wikipedia.org/wiki/Diversity_index#Shannon_index)
- Natural logarithm in Java: `Math.log()`