package com.vpr.app.security.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.security.dto.request.RegistrationRequest;
import com.vpr.app.security.entity.User;
import com.vpr.app.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Users", description = "Admin-only API for managing users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  /**
   * Retrieves a list of all users.
   *
   * @return list of users
   */
  @GetMapping
  @Operation(summary = "Get all users", description = "Retrieves a list of all registered users.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  /**
   * Retrieves a user by ID.
   *
   * @param id the ID of the user
   *
   * @return the user with the specified ID
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the user")
  @ApiResponse(responseCode = "404", description = "User not found")
  public ResponseEntity<User> getUserById(@PathVariable(name = "id") long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  /**
   * Creates a new user (admin or doctor).
   *
   * @param registrationRequest the registration details including role
   *
   * @return the created user
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new user", description = "Creates a new user with the specified role. Only accessible to admins.")
  @ApiResponse(responseCode = "201", description = "User successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  public ResponseEntity<User> registerUser(@RequestBody RegistrationRequest registrationRequest) {
    User user = userService.createUser(registrationRequest);
    return ResponseEntity.status(CREATED).body(user);
  }

  /**
   * Updates an existing user.
   *
   * @param user the updated user entity
   *
   * @return the updated user
   */
  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Update a user", description = "Updates an existing user. Only accessible to admins.")
  @ApiResponse(responseCode = "200", description = "User successfully updated")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @ApiResponse(responseCode = "404", description = "User not found")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    User updatedUser = userService.updateUser(user);
    return ResponseEntity.ok(updatedUser);
  }

  /**
   * Deletes a user by ID.
   *
   * @param id the ID of the user to delete
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a user", description = "Deletes a user by their unique ID. Only accessible to admins.")
  @ApiResponse(responseCode = "204", description = "User successfully deleted")
  @ApiResponse(responseCode = "404", description = "User not found")
  public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}

