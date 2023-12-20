package com.example.blog.application.controller;

import com.example.blog.application.exceptions.ResourceNotFoundException;
import com.example.blog.application.payload.ApiResponse;
import com.example.blog.application.payload.UserDto;
import com.example.blog.application.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;


//Swagger
@SecurityRequirement(name = "scheme1")


@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "This is User Controller")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;




    @PostMapping("/")
// Swagger
    @Operation(summary = "create new user",description = "this is user Api")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "UnAuthzied"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful OR Ok"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "New User Created")

    })
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
            UserDto usercreated= this.userService.createUser(userDto);
            return new ResponseEntity<>(usercreated, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
        UserDto updateUser = this.userService.updateUser(userDto, uid);
        if (updateUser == null) {
            throw new RuntimeException("User Id Not Found: " + uid);
        }
        return ResponseEntity.ok(updateUser);
    }
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);

        // Set appropriate values for the status and time fields
        String status = "OK"; // Set the appropriate status
        String time = "current_timestamp"; // Set the appropriate timestamp

        ApiResponse apiResponse = new ApiResponse("User Deleted", true, status, time);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> singleUser(@PathVariable("userId") Integer userId) {

        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


}

