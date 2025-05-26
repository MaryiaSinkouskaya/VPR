package com.vpr.app.security.controller;

import com.vpr.app.security.dto.RegistrationRequest;
import com.vpr.app.security.dto.User;
import com.vpr.app.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getAllUser(@PathVariable(name = "id") long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> registerUser(@RequestBody RegistrationRequest registrationRequest) {
    User user = userService.createUser(registrationRequest);
    return ResponseEntity.ok(user);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    User userUpdated = userService.updateUser(user);
    return ResponseEntity.ok(userUpdated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable(name = "id") long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
