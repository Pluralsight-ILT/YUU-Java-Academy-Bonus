---
name: "Bug #2: Wrong HTTP Method Annotation - APPLICATION WON'T START"
about: Fix incorrect HTTP method annotation causing app startup failure
labels: bug, critical-priority, blocking
title: "Bug #2: Wrong HTTP Method Annotation - APPLICATION WON'T START"
---

## Bug #2: Wrong HTTP Method Annotation

**Severity:** CRITICAL - App Startup Failure  
**Component:** UserController - deleteUser method  
**Priority:** Must Fix FIRST (Before Any Testing)

### ⚠️ CRITICAL WARNING
**This bug prevents the Spring Boot application from starting. You cannot test ANY endpoints until this is fixed.**

### Symptom
The Spring Boot application FAILS TO START with an error about "Ambiguous mapping" for `/api/users/{id}`. The error message says two methods are trying to handle the same GET request path.

### Steps to Reproduce
1. Try to start the Spring Boot application
2. Observe application fails to start
3. Check console logs for error: "Ambiguous mapping. Cannot map 'userController' method"
4. Error mentions both getUserById and deleteUser methods conflict

### Expected Behavior
- Application should start successfully
- DELETE /api/users/{id} should be handled by deleteUser method
- GET /api/users/{id} should be handled by getUserById method

### Actual Behavior
- Application refuses to start
- Spring Boot detects two methods with identical mappings: GET /api/users/{id}
- Error: "There is already 'userController' bean method mapped"

### Error Message Example
```
Ambiguous mapping. Cannot map 'userController' method 
com.mangomusic.controller.UserController#getUserById(int)
to {GET [/api/users/{id}]}: There is already 'userController' bean method
com.mangomusic.controller.UserController#deleteUser(int) mapped.
```

### Debugging Tips
- **CRITICAL:** This bug prevents the application from starting - you MUST fix it before you can test ANY endpoints
- Look at BOTH getUserById and deleteUser methods in UserController.java
- What HTTP method annotations do they each have?
- Are both methods using @GetMapping for the same path?
- What annotation should be used for DELETE requests?
- Compare with deleteArtist in ArtistController - what annotation does it use?

### Success Criteria
- ✅ Application starts successfully without errors
- ✅ DELETE /api/users/999999 returns 404 Not Found
- ✅ GET /api/users/1 returns 200 OK with user data
- ✅ Insomnia test "Users - Delete" passes

### Related Files
- `src/main/java/com/mangomusic/controller/UserController.java`