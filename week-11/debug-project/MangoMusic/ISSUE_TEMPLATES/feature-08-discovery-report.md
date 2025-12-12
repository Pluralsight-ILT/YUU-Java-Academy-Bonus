---
name: "Feature #8: Artist Discovery Report"
about: Identify high-engagement artists with fewer listeners (hidden gems)
labels: feature, medium
title: "Feature #8: Artist Discovery Report"
---

## Feature #8: Artist Discovery Report

**Difficulty:** Medium  
**Endpoint:** GET /api/artists/discovery?minPlays=10  
**Estimated Time:** 1-1.5 hours

### Business Need
Identify "hidden gems" - artists with high engagement but not well-known yet. These artists have fewer unique listeners but those listeners are highly engaged (many plays per listener). Perfect for playlist curation and artist promotion.

### Technical Requirements

**Request:**
- Method: GET
- URL: `/api/artists/discovery?minPlays=10`
- Query Parameter: minPlays (integer, optional, default: 10)

**Response Format:**
```json
[
  {
    "artistId": 156,
    "name": "The Midnight Wanderers",
    "primaryGenre": "indie",
    "uniqueListeners": 23,
    "totalPlays": 487,
    "playsPerListener": 21.2
  }
]
```

### Business Rules
- ✅ Only include artists with at least minPlays total plays
- ✅ playsPerListener = totalPlays / uniqueListeners (round to 1 decimal)
- ✅ Sort by playsPerListener DESC (highest engagement first)
- ✅ Tie-breaker: artist name alphabetically (ASC)
- ✅ Limit results to top 10 artists
- ✅ minPlays constraints: minimum 1, maximum 100, default 10

### Implementation Checklist
- [ ] Create `ArtistController.getDiscoveryReport()` method with @RequestParam
- [ ] Create `ArtistService.getDiscoveryReport()` method
- [ ] Validate and cap minPlays (min 1, max 100)
- [ ] Create `ArtistDao.getArtistEngagementStats()` method
- [ ] Implement complex JOIN query (artists → albums → album_plays)
- [ ] Calculate playsPerListener in Java (or SQL if possible)
- [ ] Implement proper sorting with tie-breaker
- [ ] Limit to 10 results
- [ ] Test with Insomnia
- [ ] Verify different minPlays values return expected results

### Testing Checklist
- [ ] minPlays=10 returns artists with at least 10 plays
- [ ] minPlays=1 returns more results (lower threshold)
- [ ] minPlays=500 returns fewer results (higher threshold)
- [ ] playsPerListener calculation is correct
- [ ] Sorting: highest playsPerListener first
- [ ] Tie-breaker works: alphabetical by name
- [ ] Maximum 10 results returned

### Related Files
- `src/main/java/com/mangomusic/controller/ArtistController.java`
- `src/main/java/com/mangomusic/service/ArtistService.java`
- `src/main/java/com/mangomusic/dao/ArtistDao.java`

### SQL Hints
- Use `COUNT(DISTINCT ap.user_id)` for unique listeners
- Use `COUNT(ap.play_id)` for total plays
- Use `HAVING` clause to filter by minPlays
- May need to calculate ratio in Java for proper sorting

### Reference
See `feature_tickets-bonus.md` for complete implementation details.