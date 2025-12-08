# MangoMusic API - Bug Tickets

Your task is to debug and fix the MangoMusic Spring Boot API. There are 8 bugs preventing the application from working correctly. Fix them one at a time, testing after each fix to ensure it works.

---

## **Bug #1: Missing @RestController Annotation**
**Severity:** High  
**Component:** AlbumController  
**Priority:** Fix First

**Symptom:**
All album endpoints return 404 Not Found even though the controller class exists and has methods defined.

**Steps to Reproduce:**
1. Start the Spring Boot application
2. Make request to GET http://localhost:8080/api/albums
3. Observe 404 Not Found error
4. Try other album endpoints - all return 404

**Expected Behavior:**
- GET /api/albums should return 200 OK with list of albums
- All album endpoints should be accessible

**Actual Behavior:**
- All album endpoints return 404 Not Found
- Spring Boot doesn't recognize AlbumController as a REST controller

**Debugging Tips:**
- Check the class-level annotations on AlbumController.java
- Compare with other working controllers like ArtistController
- What annotation tells Spring "this class handles REST API requests"?
- Spring needs to know this class should handle HTTP requests

**Success Criteria:**
- GET /api/albums returns 200 OK with album data
- All album CRUD endpoints are accessible
- Insomnia tests for albums pass

---

## **Bug #2: Wrong HTTP Method Annotation**
**Severity:** Medium  
**Component:** UserController - deleteUser method  
**Priority:** Fix Second

**Symptom:**
When attempting to delete a user via DELETE request, the server responds with 405 Method Not Allowed. The endpoint exists but won't accept DELETE requests.

**Steps to Reproduce:**
1. Make DELETE request to http://localhost:8080/api/users/999
2. Observe 405 Method Not Allowed error
3. Check server logs - no method found for DELETE

**Expected Behavior:**
- DELETE /api/users/999 should return 404 Not Found (user doesn't exist)
- DELETE /api/users/1 should return 204 No Content (successful deletion)

**Actual Behavior:**
- DELETE requests return 405 Method Not Allowed
- Server indicates no handler method for DELETE at this path

**Debugging Tips:**
- Look at the deleteUser method in UserController.java
- What annotation is currently on the method?
- What annotation should be used for DELETE requests?
- Compare with the deleteArtist method in ArtistController
- Is the method mapped to the right HTTP verb?

**Success Criteria:**
- DELETE /api/users/999 returns 404 Not Found
- Insomnia test "Users - Delete" passes

---

## **Bug #3: Missing @PathVariable Annotation**
**Severity:** High  
**Component:** ArtistController - getArtistById method  
**Priority:** Fix Third

**Symptom:**
When trying to get a specific artist by ID, the server returns 500 Internal Server Error with a message about parameter binding failure or missing required parameter.

**Steps to Reproduce:**
1. Make GET request to http://localhost:8080/api/artists/1
2. Observe 500 Internal Server Error
3. Check logs for binding/parameter errors

**Expected Behavior:**
- GET /api/artists/1 should return 200 OK with artist data for ID 1
- The {id} from the URL path should bind to the method parameter

**Actual Behavior:**
- Returns 500 error
- Spring cannot bind the URL path parameter to the method parameter

**Debugging Tips:**
- Look at the getArtistById method signature in ArtistController.java
- The method has `int id` as a parameter, but how does Spring know where to get this value?
- Compare with getAlbumById in AlbumController - what annotation does it have on its parameter?
- What annotation tells Spring "get this value from the URL path"?
- The URL has {id} but the parameter doesn't know it should come from there

**Success Criteria:**
- GET /api/artists/1 returns 200 OK with artist data
- Artist ID correctly maps from URL to method parameter
- Insomnia test "Artists - Get by ID" passes

---

## **Bug #4: Missing @RequestBody Annotation**
**Severity:** High  
**Component:** AlbumPlayController - createPlay method  
**Priority:** Fix Fourth

**Symptom:**
When attempting to create a new play via POST request with valid JSON in the request body, the server returns 400 Bad Request or the service layer receives a null object causing NullPointerException.

**Steps to Reproduce:**
1. Make POST request to http://localhost:8080/api/plays
2. Include valid JSON body:
~~~json
{
  "userId": 1,
  "albumId": 1,
  "playedAt": "2025-12-05T10:30:00",
  "completed": true
}
~~~
3. Observe 400 Bad Request or 500 error with NullPointerException

**Expected Behavior:**
- POST /api/plays should return 201 Created
- Should return the created play object with a generated playId

**Actual Behavior:**
- Returns 400 Bad Request or 500 error
- The AlbumPlay object parameter in the method is null
- Request body is not being deserialized into the Java object

**Debugging Tips:**
- Look at the createPlay method signature in AlbumPlayController.java
- The method has `AlbumPlay play` as a parameter, but Spring doesn't know this should come from the request body
- Compare with createArtist in ArtistController - what annotation does its parameter have?
- What annotation tells Spring "deserialize the JSON body into this object"?
- Without this annotation, Spring doesn't know the JSON should map to the parameter

**Success Criteria:**
- POST /api/plays returns 201 Created
- Returns play object with generated playId
- Insomnia test "Plays - Create (Log Play)" passes

---

## **Bug #5: Incorrect SQL Column Name in DAO**
**Severity:** High  
**Component:** AlbumDao - getRecentAlbums method  
**Priority:** Fix Fifth

**Symptom:**
When trying to fetch recent albums, the application throws SQLException with message about unknown column 'year' in field list or 'year' doesn't exist.

**Steps to Reproduce:**
1. Make GET request to http://localhost:8080/api/albums/recent?limit=5
2. Observe 500 Internal Server Error
3. Check logs for SQLException mentioning unknown column 'year'

**Expected Behavior:**
- GET /api/albums/recent should return 200 OK
- Should return list of albums released in the last 2 years

**Actual Behavior:**
- Returns 500 error
- SQLException: Unknown column 'year' in field list

**Debugging Tips:**
- Look at the SQL query in AlbumDao.getRecentAlbums()
- Check what columns are being selected or referenced in the query
- Look at the database schema or other working queries - what is the actual column name for the release year?
- Compare with other queries in AlbumDao that work correctly
- The error message tells you exactly what column name is wrong

**Success Criteria:**
- GET /api/albums/recent returns 200 OK
- Returns albums from the last 2 years
- Insomnia test "Albums - Get Recent Releases" passes

---

## **Bug #6: Wrong Comparison Operator in Service Validation**
**Severity:** Medium  
**Component:** AlbumService - getRecentAlbums method  
**Priority:** Fix Sixth

**Symptom:**
When requesting recent albums with a reasonable limit parameter (like 10 or 50), the endpoint returns 200 OK but with an empty array, even though recent albums exist in the database. If you pass limit=200, it suddenly works and returns albums.

**Steps to Reproduce:**
1. Make GET request to http://localhost:8080/api/albums/recent?limit=10
2. Observe 200 OK but empty array []
3. Make GET request to http://localhost:8080/api/albums/recent?limit=200
4. Observe 200 OK with albums returned (unexpected!)

**Expected Behavior:**
- Should return albums for any reasonable limit value between 1 and 100
- limit=10 should work perfectly fine
- limit=200 should be capped at 100

**Actual Behavior:**
- Small valid limits (1-99) return empty arrays
- Large limits (100+) work but shouldn't
- The validation logic is backwards

**Debugging Tips:**
- Look at the validation logic in AlbumService.getRecentAlbums()
- What happens when limit is less than 100? What should happen?
- What happens when limit is greater than 100? What should happen?
- Compare with AlbumPlayService.getUserRecentPlays() - how does it validate the limit?
- Think about the logic: "If limit is greater than 100, cap it at 100"
- The behavior is opposite of what it should be

**Success Criteria:**
- GET /api/albums/recent?limit=5 returns 5 albums (or fewer if less than 5 exist)
- GET /api/albums/recent?limit=200 returns maximum of 100 albums
- Insomnia test "Albums - Get Recent Releases" passes

---

## **Bug #7: Off-by-One Error in Pagination Logic**
**Severity:** Low  
**Component:** AlbumPlayService - getUserRecentPlays method  
**Priority:** Fix Seventh

**Symptom:**
When requesting a user's recent plays with a limit parameter, the API consistently returns one fewer result than requested. For example, requesting limit=20 returns only 19 plays, limit=5 returns only 4 plays.

**Steps to Reproduce:**
1. Make GET request to http://localhost:8080/api/plays/user/1?limit=20
2. Count the number of plays returned
3. Observe only 19 plays returned instead of 20
4. Try with limit=5, observe only 4 plays returned

**Expected Behavior:**
- GET /api/plays/user/1?limit=20 should return exactly 20 plays (or fewer if user has fewer than 20 total plays)
- The limit parameter should be honored exactly

**Actual Behavior:**
- Returns limit - 1 results
- Always one fewer than requested

**Debugging Tips:**
- Look at the validation logic in AlbumPlayService.getUserRecentPlays()
- Is the limit being adjusted or modified anywhere?
- Trace through the logic: if limit comes in as 20, what value gets passed to the DAO?
- Check for any arithmetic operations on the limit value
- Classic off-by-one error - something is subtracting when it shouldn't be

**Success Criteria:**
- GET /api/plays/user/1?limit=20 returns exactly 20 plays (if user has at least 20)
- The limit parameter is honored correctly
- Insomnia test "Plays - Get User Recent Plays" passes

---

## **Bug #8: Missing WHERE Clause in Update Query**
**Severity:** Critical  
**Component:** UserDao - updateUser method  
**Priority:** Fix Eighth (IMPORTANT!)

**Symptom:**
When attempting to update a specific user via PUT request, the operation appears successful (returns 200 OK), but ALL users in the database get updated with the same data. This is a serious data corruption issue.

**Steps to Reproduce:**
1. Check current data: GET http://localhost:8080/api/users/1
2. Check current data: GET http://localhost:8080/api/users/2
3. Note they have different usernames
4. Make PUT request to http://localhost:8080/api/users/1 with body:
~~~json
{
  "username": "testupdate",
  "email": "updated@example.com",
  "signupDate": "2025-12-05",
  "subscriptionType": "premium",
  "country": "US"
}
~~~
5. Check user 1: GET http://localhost:8080/api/users/1 - updated correctly
6. Check user 2: GET http://localhost:8080/api/users/2 - ALSO updated with same data!
7. All users in database now have identical data

**Expected Behavior:**
- PUT /api/users/1 should ONLY update the user with userId = 1
- Other users should remain unchanged
- Should return 200 OK with the updated user

**Actual Behavior:**
- ALL users in the database get updated with the same data
- Data corruption across entire users table
- No error is thrown - operation appears successful

**Debugging Tips:**
- Look at the SQL query in UserDao.updateUser()
- Check the structure of the UPDATE statement carefully
- Compare with updateAlbum in AlbumDao - what's different about the SQL?
- Think about SQL: what clause restricts which rows get updated?
- Without proper restrictions, UPDATE affects how many rows?

**IMPORTANT WARNING:**
This bug causes data corruption! After reproducing this bug, you may want to restore your database from the dump file before continuing testing.

**Success Criteria:**
- PUT /api/users/1 only updates user with ID 1
- Other users remain unchanged
- Insomnia test "Users - Update" passes
- Database integrity is maintained

---

## Testing Your Fixes

After fixing each bug:
1. Restart your Spring Boot application
2. Test the specific endpoint in Insomnia
3. Run the full Insomnia test suite to ensure no regressions
4. Move to the next bug

## Common Debugging Tools

**Check Spring Boot Logs:**
- Look for SQLException messages
- Look for "No mapping for GET/POST/etc" messages
- Look for parameter binding errors

**Use Insomnia:**
- Test endpoints individually
- Check response status codes
- Examine response bodies for error messages

**Verify with MySQL Workbench:**
- Check that data is being updated correctly
- Run manual queries to verify expectations
- Check table structure (column names, data types)

## Final Verification

Once all bugs are fixed, run the complete Insomnia test suite. All tests should pass:
- ✅ Home and Health Check
- ✅ All Artist endpoints (including top album)
- ✅ All Album endpoints (including play count, recent, trending)
- ✅ All User endpoints (including favorite genre)
- ✅ All Play endpoints
- ✅ All Report endpoints

Good luck debugging!