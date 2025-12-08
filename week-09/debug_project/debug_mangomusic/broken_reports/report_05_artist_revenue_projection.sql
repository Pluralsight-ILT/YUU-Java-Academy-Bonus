USE mangomusic;

-- REPORT 5: Artist Revenue Projection
-- Business need: Calculate estimated revenue per artist based on plays
-- Premium plays = $0.004 per play, Free plays = $0.001 per play

SELECT 
    ar.name as artist_name,
    ar.primary_genre,
    COUNT(ap.play_id) as total_plays,
    SUM(CASE WHEN u.subscription_type = 'premium' THEN 1 ELSE 0 END) as premium_plays,
    SUM(CASE WHEN u.subscription_type = 'free' THEN 1 ELSE 0 END) as free_plays,
    ROUND(
        SUM(CASE WHEN u.subscription_type = 'premium' THEN 1 ELSE 4 END) / 1000,
        2
    ) as estimated_revenue_usd
FROM album_plays ap
JOIN albums al ON ap.album_id = al.album_id
JOIN artists ar ON al.artist_id = ar.artist_id
JOIN users u ON ap.user_id = u.user_id
GROUP BY ar.artist_id, ar.name, ar.primary_genre
ORDER BY estimated_revenue_usd DESC
LIMIT 50;