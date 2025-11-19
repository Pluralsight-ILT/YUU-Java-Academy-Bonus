# MangoMusic Analytics Reports - Feature Requests

## **Feature #1: Listening Activity by Subscription Type**
**Priority:** High  
**Component:** User Engagement Analytics  
**Requested By:** Product Team

**Business Need:**
The product team wants to understand the difference in listening behavior between free and premium users. This will help us measure the value of premium subscriptions and identify if we need to adjust our premium features to drive more engagement.

**Feature Description:**
Create a report that shows total plays and average plays per user, grouped by subscription type (free vs premium). This simple comparison will reveal which user group is more engaged with the platform.

**Required Output Columns:**
- `subscription_type` - Either "free" or "premium"
- `total_users` - Count of distinct users with this subscription type
- `total_plays` - Total album plays for this subscription group
- `avg_plays_per_user` - Average plays per user, rounded to 2 decimals

**Business Rules:**
- Include all historical data (no date filter needed)
- Calculate average as: total_plays / total_users
- Group by subscription type
- Order results by subscription_type

**Expected Insights:**
Product team hypothesizes that premium users listen 2-3x more than free users. If the gap is smaller, we may need to add more premium features. If the gap is larger, our premium offering is working well.

**Steps to Verify Your Report:**
1. Check that exactly 2 rows are returned (free and premium)
2. Verify total_users by running: `SELECT subscription_type, COUNT(*) FROM users GROUP BY subscription_type;`
3. Verify total_plays by running: `SELECT u.subscription_type, COUNT(ap.play_id) FROM album_plays ap JOIN users u ON ap.user_id = u.user_id GROUP BY u.subscription_type;`
4. Manually calculate: total_plays ÷ total_users - should match your avg_plays_per_user
5. Check that avg_plays_per_user is rounded to 2 decimal places

**Success Criteria:**
- Report shows exactly 2 rows (one for free, one for premium)
- total_users matches actual user counts per subscription type
- total_plays matches actual play counts per subscription type
- avg_plays_per_user is correctly calculated and rounded
- Results help product team validate premium subscription value

---

## **Feature #2: Most Active Countries Report**
**Priority:** High  
**Component:** International Growth Analytics  
**Requested By:** International Marketing Team

**Business Need:**
We're planning international expansion and marketing campaigns. We need to identify which countries have the most active user bases so we can prioritize where to invest in localized content, partnerships, and marketing efforts.

**Feature Description:**
Create a report showing the top 15 countries by total number of album plays. This helps us understand where our platform is most popular and which markets are worth additional investment.

**Required Output Columns:**
- `country` - Two-letter country code
- `total_users` - Count of distinct users from this country
- `total_plays` - Total album plays from users in this country
- `avg_plays_per_user` - Average plays per user in this country, rounded to 2 decimals

**Business Rules:**
- Use all historical play data (no date filter)
- Group by country from users table
- Join users to album_plays to get play counts
- Order by total_plays descending (highest first)
- Show top 15 countries only (use LIMIT)

**Expected Insights:**
Marketing team expects US, UK, and Canada to be top countries, but wants to discover emerging markets. High plays per user indicates engaged audiences worth targeting.

**Steps to Verify Your Report:**
1. Check that exactly 15 rows are returned
2. Verify first row has highest total_plays, last row has 15th highest
3. Check a specific country: `SELECT country, COUNT(DISTINCT u.user_id), COUNT(ap.play_id) FROM users u JOIN album_plays ap ON u.user_id = ap.user_id WHERE country = 'US' GROUP BY country;`
4. Confirm US (or top country) matches your report's top row
5. Manually calculate avg for one country to verify formula is correct

**Success Criteria:**
- Shows exactly 15 rows (top 15 countries)
- Countries are properly ranked by total_plays (descending)
- No duplicate country codes
- avg_plays_per_user is correctly calculated and rounded
- Helps marketing team prioritize regional campaigns

---

## **Feature #3: Genre Popularity Rankings**
**Priority:** Medium  
**Component:** Content Strategy Analytics  
**Requested By:** Content Acquisition Team

**Business Need:**
The content team needs to understand which music genres are most popular on our platform. This will guide licensing negotiations - we should invest more in licensing agreements for popular genres and less in genres that users don't engage with.

**Feature Description:**
Create a report showing all genres ranked by total number of album plays. This gives content team a clear picture of what users want to listen to most.

**Required Output Columns:**
- `genre` - The genre name (from artists.primary_genre)
- `total_albums` - Count of distinct albums in this genre that were played
- `total_plays` - Total album plays for this genre
- `total_artists` - Count of distinct artists in this genre that were played

**Business Rules:**
- Include all historical play data (no date filter)
- Join album_plays → albums → artists to get genre
- Group by primary_genre from artists table
- Order by total_plays descending (most popular first)
- Include all genres (no limit)

**Expected Insights:**
Content team expects rock, pop, and hip hop to be top genres. If jazz or classical are unexpectedly high, we should reconsider our licensing strategy in those areas.

**Steps to Verify Your Report:**
1. Count total genres: `SELECT COUNT(DISTINCT primary_genre) FROM artists;` - your report should have this many rows
2. Check top genre matches: `SELECT ar.primary_genre, COUNT(ap.play_id) FROM album_plays ap JOIN albums al ON ap.album_id = al.album_id JOIN artists ar ON al.artist_id = ar.artist_id GROUP BY ar.primary_genre ORDER BY COUNT(ap.play_id) DESC LIMIT 1;`
3. Verify no duplicate genres appear
4. Confirm total_plays is properly counted (not inflated by incorrect JOINs)
5. Spot-check that total_albums and total_artists are using DISTINCT correctly

**Success Criteria:**
- Shows all genres present in the data (one row per genre)
- Genres are properly ranked by popularity (total_plays descending)
- No duplicate genre entries
- Accurate counts for albums, plays, and artists
- Helps content team allocate licensing budget effectively

---

## **Feature #4: User Growth by Month Report**
**Priority:** Medium  
**Component:** Growth Analytics  
**Requested By:** Executive Team

**Business Need:**
The executive team needs to track user acquisition over time for board presentations and investor updates. We need a simple month-by-month view of how many users signed up, showing our growth trajectory.

**Feature Description:**
Create a report showing the number of new user signups for each month over the past 12 months. This provides a clear timeline of user acquisition trends.

**Required Output Columns:**
- `signup_month` - The month users signed up (format: YYYY-MM)
- `new_users` - Count of users who signed up in that month
- `free_signups` - Count of users who signed up as free
- `premium_signups` - Count of users who signed up as premium

**Business Rules:**
- Group users by their signup_date month
- Use DATE_FORMAT(signup_date, '%Y-%m') to format as YYYY-MM
- Filter to only show past 12 months: `WHERE signup_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)`
- Use CASE or SUM with CASE to count free vs premium separately
- Order by signup_month ascending (oldest to newest)

**Expected Insights:**
Executive team wants to see steady month-over-month growth. Any months with declining signups need investigation. The free vs premium split shows if our pricing is working.

**Steps to Verify Your Report:**
1. Check that approximately 12 rows are returned (could be less if platform is newer)
2. Verify signup_month format is YYYY-MM (like '2024-11' not '11-2024')
3. For one month, manually verify: `SELECT COUNT(*) FROM users WHERE DATE_FORMAT(signup_date, '%Y-%m') = '2024-10';`
4. Check that new_users = free_signups + premium_signups for each row
5. Confirm months are in chronological order (oldest first)

**Success Criteria:**
- Shows one row per month for past 12 months
- signup_month is properly formatted as YYYY-MM
- Accurate count of signups per month
- Free and premium counts sum to total new_users
- Clear trend line of user growth visible
- Ready for executive presentation

---

## **Feature #5: Album Completion Rate Summary**
**Priority:** Medium  
**Component:** Content Quality Metrics  
**Requested By:** Content Acquisition Team

**Business Need:**
We need to understand overall platform engagement quality. Do users actually finish listening to albums, or do they skip around? A high completion rate indicates our content is engaging. Low completion rate means users are dissatisfied with what they find.

**Feature Description:**
Create a simple summary report showing how many album plays were completed vs incomplete, along with the overall completion percentage for the entire platform.

**Required Output Columns:**
- `play_status` - Either "Completed" or "Incomplete"
- `total_plays` - Count of plays with this status
- `percentage` - Percentage of total plays, rounded to 2 decimals

**Business Rules:**
- Use the completed boolean column (TRUE = Completed, FALSE = Incomplete)
- Use CASE statement to convert TRUE/FALSE to "Completed"/"Incomplete" labels
- Include all historical play data (no date filter)
- Calculate percentage as: (plays with this status / total all plays) × 100
- Round percentage to 2 decimal places
- Show 2 rows total (one for completed, one for incomplete)

**Expected Insights:**
Content team considers 60%+ completion rate as healthy. Below 50% indicates content quality issues. This metric validates if users are satisfied with what they're hearing.

**Steps to Verify Your Report:**
1. Check that exactly 2 rows are returned (Completed and Incomplete)
2. Verify total plays: `SELECT COUNT(*) FROM album_plays;` should equal sum of your two total_plays values
3. Check completed count: `SELECT COUNT(*) FROM album_plays WHERE completed = TRUE;`
4. Manually calculate percentage: (completed_count / total_count) × 100
5. Verify percentages sum to 100% (or very close due to rounding)

**Success Criteria:**
- Shows exactly 2 rows (Completed and Incomplete)
- Labels are clear ("Completed" not "TRUE" or "1")
- total_plays counts are accurate
- Percentages sum to 100%
- Percentages are rounded to 2 decimal places
- Clear view of overall platform engagement quality

---

## **Feature #6: Top 20 Artists by Play Count**
**Priority:** High  
**Component:** Artist Performance Analytics  
**Requested By:** Artist Relations Team

**Business Need:**
The artist relations team needs to identify our most popular artists to prioritize for exclusive content deals, playlist features, and promotional partnerships. We want to invest more in relationships with artists who drive the most listening activity.

**Feature Description:**
Create a report showing the top 20 artists ranked by total album plays. This helps artist relations team know which artists are most valuable to our platform.

**Required Output Columns:**
- `artist_name` - Name of the artist
- `genre` - Primary genre of the artist
- `total_albums_played` - Count of distinct albums by this artist that were played
- `total_plays` - Total plays across all of this artist's albums
- `unique_listeners` - Count of distinct users who played this artist

**Business Rules:**
- Include all historical play data (no date filter)
- Join album_plays → albums → artists
- Group by artist (include artist_id, name, and genre in GROUP BY)
- Use COUNT(DISTINCT album_id) for albums count
- Use COUNT(play_id) for total plays
- Use COUNT(DISTINCT user_id) for unique listeners
- Order by total_plays descending (highest first)
- Show top 20 artists only (use LIMIT)

**Expected Insights:**
Artist relations expects mainstream pop/hip-hop artists at the top, but wants to identify rising artists with strong engagement. High unique_listeners with high plays indicates broad appeal worth investing in.

**Steps to Verify Your Report:**
1. Check that exactly 20 rows are returned
2. Verify first row has highest total_plays, last row has 20th highest
3. Pick one artist and manually verify: `SELECT ar.name, COUNT(DISTINCT al.album_id), COUNT(ap.play_id), COUNT(DISTINCT ap.user_id) FROM album_plays ap JOIN albums al ON ap.album_id = al.album_id JOIN artists ar ON al.artist_id = ar.artist_id WHERE ar.name = 'Taylor Swift' GROUP BY ar.name;`
4. Confirm no duplicate artist names
5. Check that an artist's unique_listeners is never greater than total users in platform

**Success Criteria:**
- Shows exactly 20 rows (top 20 artists)
- Artists are properly ranked by total_plays (descending)
- No duplicate artist names
- All counts are accurate (albums, plays, listeners)
- Helps artist relations prioritize partnership opportunities
- Clear view of who drives platform listening

---

## **General Implementation Notes**

**SQL Concepts Needed:**
- SELECT with column aliases
- JOIN (2-3 tables: albums to artists, plays to albums, plays to users)
- WHERE clauses for filtering
- GROUP BY for aggregation
- COUNT(), SUM(), AVG() aggregate functions
- COUNT(DISTINCT column) for unique counts
- ORDER BY with DESC/ASC
- LIMIT for top N results
- DATE_FORMAT() for grouping by month
- ROUND() for decimal precision
- Basic CASE statements for conditional logic
- Subquery for percentage calculation (Feature #5)

**For All Reports:**
- Use clear, descriptive column aliases
- Include header comments explaining business context
- Round decimals to 2 places where specified
- Order results in most useful way for business users
- Test with actual database to verify results
- Make sure JOINs don't create duplicate rows

**Testing Each Report:**
1. Verify output has all required columns with correct names
2. Check that row count matches expectations
3. Manually verify calculations for sample rows
4. Confirm GROUP BY includes all non-aggregated columns
5. Ensure JOINs are correct (compare to verification queries)
6. Test that numbers make logical business sense

**Delivery:**
- Each report in its own .sql file
- File naming: `feature_report_0X_descriptive_name.sql`
- Include header comment with business context
- Test against mangomusic database
- Document any assumptions or design decisions