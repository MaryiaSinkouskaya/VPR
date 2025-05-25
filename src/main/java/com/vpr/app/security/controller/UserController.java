package com.vpr.app.security.controller;

import com.vpr.app.dto.request.AbnormalityRequestDto;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.security.dto.AuthenticationRequest;
import com.vpr.app.security.dto.AuthenticationResponse;
import com.vpr.app.security.dto.RegistrationRequest;
import com.vpr.app.security.dto.User;
import com.vpr.app.security.service.AuthenticationService;
import com.vpr.app.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {//todo only admin can access the endpoints

    private final AuthenticationService authenticationService;
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
        //todo register with any role
        return null;
    }
}
