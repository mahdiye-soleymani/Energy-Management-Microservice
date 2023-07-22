package com.bankino.energymanagement.controller;

import com.bankino.energymanagement.dto.UserDTO;
import com.bankino.energymanagement.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Create a new user")
    @ApiResponse(code = 201, message = "User created successfully")
    @PostMapping("/create-user")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO UserDTO) {
        UserDTO createdUser = userService.createUser(UserDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @ApiOperation("Get all users")
    @ApiResponse(code = 200, message = "Successfully retrieved users")
    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @ApiOperation("Get a user by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Update a user by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long userId,
            @RequestBody @Valid UserDTO UserDTO
    ) {
        UserDTO updatedUser = userService.updateUser(userId, UserDTO);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete a user by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        if (userService.deleteUser(userId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
