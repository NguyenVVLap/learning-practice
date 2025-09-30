package com.example.UserService.controller;

import com.example.UserService.entity.User;
import com.example.UserService.exception.ErrorResponse;
import com.example.UserService.exception.UserNotExistException;
import com.example.UserService.exception.WrongInputException;
import com.example.UserService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Find user by email, password", description = "Return 200 if the user exists.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<User> getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) throws RuntimeException {
        return ResponseEntity.ok(userService.getUserByEmailAndPassword(email, password));
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Return 201 if the user was successfully created.")
    @ApiResponse(responseCode = "201", description = "User created successfully.")
    public ResponseEntity<String> insertUser(@Valid @RequestBody User user) {
        userService.addNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User " + user.getEmail() + " inserted");
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotExistException(UserNotExistException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(WrongInputException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotExistException(WrongInputException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidateException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        response.put("errors", errors);
        return response;
    }
}
