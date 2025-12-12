---
name: "Bug #8: Missing WHERE Clause in Update Query"
about: Fix missing WHERE clause in UserDao updateUser method - CRITICAL DATA CORRUPTION BUG
labels: bug, critical
title: "Bug #8: Missing WHERE Clause in Update Query"
---

## Bug #8: Missing WHERE Clause in Update Query

**Severity:** Critical  
**Component:** UserDao - updateUser method  
**Priority:** Fix Eighth (IMPORTANT!)

### Symptom
When attempting to update a specific user via PUT request, the operation appears successful (returns 200 OK), but ALL users in the database get updated with the same data. This is a serious data corruption issue.

### Steps to Reproduce
1. Check current data: GET http://localhost:8080/api/users/1
2. Check current data: GET http://localhost:8080/api/users/2
3. Note they have different usernames
4. Make PUT request to http://localhost:8080/api/users/1 with body:
```json
{
  "username": "testupdate",
  "email": "updated@example.com",
  "signupDate": "2025-12-05",
  "subscriptionType": "premium",
  "country": "US"
}
```
5. Check user 1: GET http://localhost:8080/api/users/1 - updated correctly
6. Check user 2: GET http://localhost:8080/api/users/2 - ALSO updated with same data!
7. All users in database now have identical data

### Expected Behavior
- PUT /api/users/1 should ONLY update the user with userId = 1
- Other users should remain unchanged
- Should return 200 OK with the updated user

### Actual Behavior
- ALL users in the database get updated with the same data
- Data corruption across entire users table
- No error is thrown - operation appears successful

### Debugging Tips
- Look at the SQL query in UserDao.updateUser()
- Check the structure of the UPDATE statement carefully
- Compare with updateAlbum in AlbumDao - what's different about the SQL?
- Think about SQL: what clause restricts which rows get updated?
- Without proper restrictions, UPDATE affects how many rows?

### IMPORTANT WARNING
This bug causes data corruption! After reproducing this bug, you may want to restore your database from the dump file before continuing testing.

### Success Criteria
- PUT /api/users/1 only updates user with ID 1
- Other users remain unchanged
- Insomnia test "Users - Update" passes
- Database integrity is maintained