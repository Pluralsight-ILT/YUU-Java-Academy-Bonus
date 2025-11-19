# MangoMusic Analytics Reports - Bug Tickets

## **Bug #1: Daily Active Users Report Shows Inflated Numbers**
**Priority:** Critical  
**Component:** Daily Active Users Report  
**Report File:** `broken_report_01_daily_active_users.sql`

**Description:**
The Daily Active Users report is showing numbers that seem way too high. When looking at a single day's data, the number doesn't make sense compared to our total user base.

**Business Impact:**
- Executive dashboard shows misleading engagement metrics
- Marketing team is celebrating user growth that doesn't actually exist
- Investors are receiving incorrect platform usage statistics

**Steps to Reproduce:**
1. Run the broken Daily Active Users report
2. Look at any single day's daily_active_users count
3. Run this check query: `SELECT COUNT(DISTINCT user_id) FROM album_plays WHERE DATE(played_at) = '2024-11-18';`
4. Compare the two numbers - they should match but don't

**Expected Behavior:**
The daily_active_users count should match the count of unique users who played albums that day.

**Actual Behavior:**
The daily_active_users count is much higher than the actual number of unique users. The report appears to be counting something other than unique users.

**Hint for Debugging:**
Check what the COUNT function is actually counting. Is it counting users or something else?

---

## **Bug #2: Top Albums Report Stuck on November Data**
**Priority:** High  
**Component:** Top 10 Most Played Albums This Month  
**Report File:** `broken_report_02_top_albums_this_month.sql`

**Description:**
The "Top Albums This Month" report is always showing the same albums from November, regardless of what month we're currently in or when the report is run.

**Business Impact:**
- Content team is promoting artists based on outdated data
- Playlist curators can't see current month's trending albums
- Monthly performance reviews use wrong time period

**Steps to Reproduce:**
1. Check today's date: `SELECT CURDATE(), MONTH(CURDATE());`
2. Run the broken Top Albums report
3. Notice the results only include plays from November (month 11)
4. If today is not November, the report is showing wrong data

**Expected Behavior:**
Report should automatically show data for whatever the current month is when the report runs.

**Actual Behavior:**
Report always shows November data no matter what month it is or when it's run.

**Hint for Debugging:**
Look at the WHERE clause. Is there a hard-coded value that should be dynamic?

---

## **Bug #3: User Retention Report Shows 0% for All Cohorts**
**Priority:** High  
**Component:** New User Retention (7-Day)  
**Report File:** `broken_report_03_user_retention_7day.sql`

**Description:**
The 7-day retention report is showing 0% (or 0) in the retention_rate_percent column for every single signup cohort. This is clearly wrong since we know users do return to the platform.

**Business Impact:**
- Growth team thinks no users are returning, causing panic
- Product decisions being made based on assumption of 0% retention
- Unable to identify which signup cohorts are performing well

**Steps to Reproduce:**
1. Run the User Retention report
2. Look at the retention_rate_percent column
3. Every single row shows 0 or 0%
4. Compare retained_users column - some cohorts have non-zero retained users but still show 0% rate

**Expected Behavior:**
When a cohort has retained users, the retention_rate_percent should show a realistic percentage like 23%, 45%, 67%, etc.

**Actual Behavior:**
All retention rates show as 0 or 0%, even for cohorts where retained_users is greater than zero.

**Hint for Debugging:**
The calculation is there, but something about how the math is being done is causing the result to be 0. Think about integer vs decimal division.

---

## **Bug #4: Monthly Active Users Report Fails to Run**
**Priority:** Critical  
**Component:** Monthly Active Users by Country  
**Report File:** `broken_report_04_monthly_active_by_country.sql`

**Description:**
The Monthly Active Users by Country report crashes with an error every time we try to run it. The error message says something about "Invalid use of group function."

**Business Impact:**
- Can't generate international expansion reports for board meetings
- Regional marketing teams have no visibility into their user activity
- Unable to identify which countries are growing vs declining

**Steps to Reproduce:**
1. Attempt to run the Monthly Active Users by Country report
2. Observe error message appears
3. Report fails completely and returns no results

**Expected Behavior:**
Report should run successfully and show monthly active user counts by country.

**Actual Behavior:**
Report crashes with SQL error: "Invalid use of group function" and returns no data at all.

**Hint for Debugging:**
The error mentions "group function" - this relates to aggregate functions like COUNT(). Check where aggregate functions are being used. Are they in the right clause?

---

## **Bug #5: Artist Revenue Report Shows Wrong Payment Amounts**
**Priority:** Critical  
**Component:** Artist Revenue Projection  
**Report File:** `broken_report_05_artist_revenue_projection.sql`

**Description:**
The Artist Revenue report is showing payment amounts that don't make logical sense. Looking at the numbers:
- Premium plays should be worth more than free plays (premium = $0.004, free = $0.001)
- But some artists with mostly free plays show higher revenue than artists with mostly premium plays
- Many artists show $0.00 revenue even though they have thousands of plays

**Business Impact:**
- Finance team can't generate accurate artist payment projections
- Licensing negotiations are using incorrect revenue data
- Artists are questioning why their payment estimates seem wrong

**Steps to Reproduce:**
1. Run the Artist Revenue Projection report
2. Look at artists with high premium_plays vs high free_plays
3. Check their estimated_revenue_usd values
4. Notice the revenue doesn't correctly reflect that premium plays should be worth 4x more than free plays
5. Many artists with significant plays show $0.00 revenue

**Expected Behavior:**
- An artist with 10,000 premium plays should show ~$40 revenue (10,000 × $0.004)
- An artist with 10,000 free plays should show ~$10 revenue (10,000 × $0.001)
- Revenue should always be higher for more plays

**Actual Behavior:**
Revenue amounts don't follow the correct rate calculation. The numbers don't match the expected premium ($0.004) and free ($0.001) rates per play.

**Hint for Debugging:**
Look at the CASE statement in the revenue calculation. Are the right values being used? Is the math formula correct?

---

## **Bug #6: Churn Risk Report Shows Same Value for All Users**
**Priority:** High  
**Component:** Churn Risk Users  
**Report File:** `broken_report_06_churn_risk_users.sql`

**Description:**
The Churn Risk report is showing the exact same "days_since_last_play" value for every single premium user in the results. All users show 0 days since last play, which makes the entire report useless for identifying at-risk users.

**Business Impact:**
- Retention team can't identify which users to target for re-engagement
- All premium users appear equally active (0 days) when some haven't played in months
- Unable to prioritize outreach efforts to prevent cancellations

**Steps to Reproduce:**
1. Run the Churn Risk Users report
2. Look at the days_since_last_play column
3. Notice all users show the exact same value (0 or close to 0)
4. Manually check: `SELECT user_id, MAX(played_at) FROM album_plays WHERE user_id IN (1, 2, 3) GROUP BY user_id;`
5. Confirm that different users actually have different last play dates

**Expected Behavior:**
Each user should show their own individual "days since last play" based on when they personally last used the app. Some users should show 15 days, others 20 days, others 45 days, etc.

**Actual Behavior:**
All users show 0 (or the same number) for days_since_last_play, making it impossible to identify who is actually at risk of churning.

**Hint for Debugging:**
The calculation uses DATEDIFF to find days between dates. Check what dates are being compared. Should it be calculating based on each individual user's data, or is it using something that's the same for everyone?

---

## **Testing Instructions**

For each bug ticket:
1. **Understand the business problem** - Read what's wrong from a user perspective
2. **Run the broken report** - See the incorrect behavior yourself
3. **Compare to working version** - Run the correct report to see what it should look like
4. **Identify the SQL issue** - Find the specific line(s) of code causing the problem
5. **Fix and test** - Make the change and verify results now match expected behavior
6. **Document your fix** - Note what was wrong and how you fixed it

**Success Criteria:**
- Daily Active Users shows accurate unique user counts
- Top Albums automatically reflects current month
- Retention rates show realistic percentages with decimals
- Monthly Active report runs without errors
- Artist Revenue correctly calculates based on $0.004 (premium) and $0.001 (free) rates
- Churn Risk shows different days_since_last_play for each user based on their actual activity