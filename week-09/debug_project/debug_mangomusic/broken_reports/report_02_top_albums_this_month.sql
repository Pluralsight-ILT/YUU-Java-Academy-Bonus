USE mangomusic;

-- REPORT 2: Top 10 Most Played Albums This Month
-- Business need: What albums are trending right now?
-- Shows album title, artist name, and play count for current month

SELECT 
    al.title as album_title,
    ar.name as artist_name,
    COUNT(*) as play_count
FROM album_plays ap
JOIN albums al ON ap.album_id = al.album_id
JOIN artists ar ON al.artist_id = ar.artist_id
WHERE YEAR(ap.played_at) = YEAR(CURDATE())
  AND MONTH(ap.played_at) = 11
GROUP BY al.album_id, al.title, ar.name
ORDER BY play_count DESC
LIMIT 10;