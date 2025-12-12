# MangoMusic API - Bug Tickets

Your task is to debug and fix the MangoMusic Spring Boot API. There are 8 bugs preventing the application from working correctly. Fix them one at a time, testing after each fix to ensure it works.

---

## **Bug #1: 404 not found**
**Severity:** High  
**Component:** AlbumController  
**Priority:** High

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

## **Bug #2: APPLICATION WON'T START**
**Severity:** CRITICAL - App Startup Failure  
**Component:** UserController - deleteUser method  
**Priority:** Critical

**Symptom:**
The Spring Boot application FAILS TO START with an error about "Ambiguous mapping" for `/api/users/{id}`. The error message says two methods are trying to handle the same GET request path.

**Steps to Reproduce:**
1. Try to start the Spring Boot application
2. Observe application fails to start
3. Check console logs for error: "Ambiguous mapping. Cannot map 'userController' method"
4. Error mentions both getUserById and deleteUser methods conflict

**Expected Behavior:**
- Application should start successfully
- DELETE /api/users/{id} should be handled by deleteUser method
- GET /api/users/{id} should be handled by getUserById method

**Actual Behavior:**
- Application refuses to start
- Spring Boot detects two methods with identical mappings: GET /api/users/{id}
- Error: "There is already 'userController' bean method mapped"

**Debugging Tips:**
- **CRITICAL:** This bug prevents the application from starting - you MUST fix it before you can test ANY endpoints
- Look at BOTH getUserById and deleteUser methods in UserController.java
- What HTTP method annotations do they each have?
- Are both methods using @GetMapping for the same path?
- What annotation should be used for DELETE requests?
- Compare with deleteArtist in ArtistController - what annotation does it use?

**Success Criteria:**
- Application starts successfully without errors
- DELETE /api/users/999999 returns 404 Not Found
- GET /api/users/1 returns 200 OK with user data
- Insomnia test "Users - Delete" passes

---

## **Bug #3: 500 Internal Server Error**
**Severity:** High  
**Component:** ArtistController - getArtistById method  
**Priority:** High

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

## **Bug #4: 400 Bad Request**
**Severity:** High  
**Component:** AlbumPlayController - createPlay method  
**Priority:** High

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

## **Bug #5: Off-by-One Error**
**Severity:** Low  
**Component:** AlbumPlayService - getUserRecentPlays method  
**Priority:** Low

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

## **Bug #6: All Updates Broken**
**Severity:** Critical  
**Component:** UserDao - updateUser method  
**Priority:** IMPORTANT!

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
1. Check user 1: GET http://localhost:8080/api/users/1 - updated correctly
2. Check user 2: GET http://localhost:8080/api/users/2 - ALSO updated with same data!
3. All users in database now have identical data

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