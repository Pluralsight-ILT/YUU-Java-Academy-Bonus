USE mangomusic;

-- REPORT 6: Churn Risk Users
-- Business need: Identify premium users at risk of canceling (haven't played in 14+ days)
-- Shows premium users, days since last play, and total lifetime plays

SELECT 
    u.user_id,
    u.username,
    u.email,
    u.country,
    DATEDIFF(CURDATE(), NOW()) as days_since_last_play,
    COUNT(ap.play_id) as lifetime_plays,
    CASE 
        WHEN DATEDIFF(CURDATE(), NOW()) >= 30 THEN 'High Risk'
        WHEN DATEDIFF(CURDATE(), NOW()) >= 21 THEN 'Medium Risk'
        WHEN DATEDIFF(CURDATE(), NOW()) >= 14 THEN 'Low Risk'
    END as churn_risk_level
FROM users u
LEFT JOIN album_plays ap ON u.user_id = ap.user_id
WHERE u.subscription_type = 'premium'
GROUP BY u.user_id, u.username, u.email, u.country
HAVING DATEDIFF(CURDATE(), MAX(ap.played_at)) >= 14
ORDER BY days_since_last_play DESC;