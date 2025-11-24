# MangoMusic JDBC Application - Bug Tickets

## **Bug #1: Artist Search Causes Performance Issues**
**Priority:** High  
**Component:** Search & Queries - Search for an artist  
**Method:** `MangoMusicDataManager.searchArtists()`

**Description:**
When searching for artists by name, the application appears to work but after running multiple searches, the application starts slowing down significantly. Over time, the application may crash or become unresponsive, especially after performing many artist searches.

**Business Impact:**
- Application becomes unusable after several searches
- Staff have to restart the application frequently
- Poor user experience and wasted time
- System resources are being consumed unnecessarily

**Steps to Reproduce:**
1. Start the application and go to "Search & Queries"
2. Select "Search for an artist"
3. Search for any artist (like "Beatles" or "Swift")
4. Return to menu and search again multiple times
5. Notice application performance degrading over time
6. After 20-30 searches, application may become very slow or crash

**Expected Behavior:**
The application should maintain consistent performance regardless of how many searches are performed. Resources should be properly cleaned up after each search.

**Actual Behavior:**
Application performance degrades with each search. Memory usage increases over time and is not released properly.

**Hint for Debugging:**
Review your JDBC resource management practices. Every database resource that is opened needs to be properly closed.

---

## **Bug #2: View Artist Albums Feature Not Working**
**Priority:** Critical  
**Component:** Search & Queries - View artist's albums  
**Method:** `MangoMusicDataManager.getAlbumsByArtist()`

**Description:**
When trying to view albums for a specific artist, the application throws an error and returns no results.

**Business Impact:**
- Cannot view artist albums at all - feature is completely broken
- Staff cannot help customers with album inquiries
- Critical functionality is unavailable

**Steps to Reproduce:**
1. Go to "Search & Queries"
2. Select "View artist's albums"
3. Enter any valid artist ID (try 1, 5, 10, etc.)
4. Application throws SQLException

**Expected Behavior:**
Should display a list of all albums by the specified artist, showing album title, artist name, and release year.

**Actual Behavior:**
Application crashes and displays an error message about the database query.

**Hint for Debugging:**
When you see a SQLException, read the error message carefully - it will tell you exactly what's wrong. Then compare your Java code to your SQL query.

---

## **Bug #3: User Search Only Finds Username Matches**
**Priority:** Medium  
**Component:** Search & Queries - Look up user by username  
**Method:** `MangoMusicDataManager.searchUsers()`

**Description:**
The user lookup feature is supposed to search both username and email fields, but it only seems to find matches in the username field. When searching for part of an email address, no results are returned even though users with that email exist.

**Business Impact:**
- Cannot look up users by email address
- Customer support limited in helping users who only know their email
- Reduced functionality for user management

**Steps to Reproduce:**
1. Go to "Search & Queries"
2. Select "Look up user by username"
3. Search for a known email pattern (like "gmail" or "example")
4. Notice no results are returned
5. Search for username instead - results are found
6. Verify: `SELECT * FROM users WHERE email LIKE '%gmail%';` - users exist with gmail addresses

**Expected Behavior:**
Searching for "gmail" should find all users with gmail.com email addresses. The search should check both username and email fields.

**Actual Behavior:**
Only username field is being searched. Email field search is not working.

**Hint for Debugging:**
When using PreparedStatement with multiple parameters, each ? placeholder needs its own unique value. Check that you're setting all parameters correctly.

---

## **Bug #4: View User Plays Causes Application Instability**
**Priority:** High  
**Component:** Search & Queries - View user's recent plays  
**Method:** `MangoMusicDataManager.getUserRecentPlays()`

**Description:**
After viewing user play history several times, the application becomes noticeably slower. Eventually, after checking 10-15 different users, the application may display errors about too many connections or run out of resources.

**Business Impact:**
- Application becomes unstable when viewing user activity
- Cannot reliably check user play history
- May affect other database operations
- System resources exhausted

**Steps to Reproduce:**
1. Go to "Search & Queries"
2. Select "View user's recent plays"
3. Enter a user ID and check their plays
4. Repeat for multiple different users (10-15 times)
5. Notice application slowing down
6. May see errors about connection limits or database resources

**Expected Behavior:**
Should be able to view user plays repeatedly without performance degradation. Database connections should be properly managed and released.

**Actual Behavior:**
Performance degrades after multiple uses. Database connections appear to not be released properly.

**Hint for Debugging:**
This method handles database resources differently than other methods. Look at how Connection and PreparedStatement are being managed compared to working methods.

---

## **Bug #5: Find Albums by Genre - Security Vulnerability**
**Priority:** Critical  
**Component:** Search & Queries - Find albums by genre  
**Method:** `MangoMusicDataManager.getAlbumsByGenre()`

**Description:**
The security team has flagged the "Find albums by genre" feature as having a potential SQL injection vulnerability. While it works for normal genre names, malicious input could potentially access or modify database data.

**Business Impact:**
- Security vulnerability could allow unauthorized database access
- Potential data breach risk
- Fails security audit requirements
- Could expose sensitive user data

**Steps to Reproduce:**
1. Go to "Search & Queries"
2. Select "Find albums by genre"  
3. Enter a normal genre like "Rock" - works fine
4. Enter SQL-like input: `Rock' OR '1'='1` - returns unexpected results
5. Security scan tools flag this as SQL injection vulnerability

**Expected Behavior:**
User input should be properly sanitized and parameterized. No matter what text is entered, it should only search for that exact genre name without executing SQL commands.

**Actual Behavior:**
Unexpected behavior occurs when entering SQL-like syntax in the genre field. The query doesn't properly protect against malicious input.

**Hint for Debugging:**
There are two ways to execute SQL queries in JDBC - one is safe, one is not. Check which approach this method is using and compare it to the other working methods.

---

## **Bug #6: Search Albums Works But Uses Poor Practice**
**Priority:** Low  
**Component:** Search & Queries - Search albums by title  
**Method:** `MangoMusicDataManager.searchAlbums()`

**Description:**
The album search feature works correctly and returns the right results, but the code review team noticed that one of the fields is being retrieved inefficiently. The code gets it as one data type and then converts it to another, which is unnecessary and could cause problems.

**Business Impact:**
- Not currently broken, but inefficient code
- Could cause issues in the future
- Not following best practices
- Unnecessary data conversion could fail unexpectedly

**Steps to Reproduce:**
1. Go to "Search & Queries"
2. Select "Search albums by title"
3. Search for any album - it works fine
4. Review the code in `searchAlbums()` method
5. Notice one field is retrieved inefficiently

**Expected Behavior:**
When retrieving data from ResultSet, should use the method that matches the database column's data type.

**Actual Behavior:**
Code retrieves a numeric field as text and then converts it, rather than getting it directly as the correct type.

**Hint for Debugging:**
Look at the different ResultSet getter methods available (getString, getInt, getLong, getDouble, etc.). Are you using the most appropriate one for each column type?

---

## **Testing Instructions**

For each bug ticket:
1. **Understand the business problem** - Read what's wrong from a user perspective
2. **Reproduce the issue** - Follow the steps to see the incorrect behavior
3. **Review the code** - Find the method mentioned and identify the JDBC issue
4. **Fix the bug** - Make the necessary changes to proper JDBC practices
5. **Test your fix** - Verify the feature now works correctly
6. **Document your fix** - Note what was wrong and how you fixed it

**Success Criteria:**
- Artist search maintains consistent performance over many searches
- View artist albums displays results without crashing
- User search finds matches in both username and email fields
- View user plays can be used repeatedly without slowdown
- Find albums by genre uses proper parameterization (no SQL injection)
- Search albums retrieves data types efficiently

**Key JDBC Concepts Being Tested:**
- Try-with-resources for proper resource management
- PreparedStatement parameter binding
- ResultSet column name accuracy
- PreparedStatement vs Statement security
- Appropriate ResultSet getter methods
- Connection and resource lifecycle management
