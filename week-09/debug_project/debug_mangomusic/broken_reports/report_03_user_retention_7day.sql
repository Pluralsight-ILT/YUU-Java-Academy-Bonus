USE mangomusic;

-- REPORT 3: New User Retention (7-Day)
-- Business need: What % of new signups come back after 7 days?
-- Shows cohorts of users by signup week with their 7-day return rate

SELECT 
    DATE_FORMAT(u.signup_date, '%Y-%W') as signup_week,
    COUNT(DISTINCT u.user_id) as total_signups,
    COUNT(DISTINCT CASE 
        WHEN ap.played_at >= DATE_ADD(u.signup_date, INTERVAL 7 DAY)
         AND ap.played_at < DATE_ADD(u.signup_date, INTERVAL 14 DAY)
        THEN u.user_id 
    END) as retained_users,
    FORMAT(COUNT(DISTINCT CASE 
        WHEN ap.played_at >= DATE_ADD(u.signup_date, INTERVAL 7 DAY)
         AND ap.played_at < DATE_ADD(u.signup_date, INTERVAL 14 DAY)
        THEN u.user_id 
    END) / COUNT(DISTINCT u.user_id), 0) as retention_rate_percent
FROM users u
LEFT JOIN album_plays ap ON u.user_id = ap.user_id
WHERE u.signup_date >= DATE_SUB(CURDATE(), INTERVAL 900 DAY)
GROUP BY DATE_FORMAT(u.signup_date, '%Y-%W')
ORDER BY signup_week DESC;