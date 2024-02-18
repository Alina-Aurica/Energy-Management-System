package com.example.usermanagementservice.controllers;

import com.example.usermanagementservice.controllers.auth.AuthenticationResponse;
import com.example.usermanagementservice.dtos.UserDTO;
import com.example.usermanagementservice.entities.Users;
import com.example.usermanagementservice.services.LogInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class LogInController {
    @Autowired
    private LogInService logInService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Users user){
        return ResponseEntity.ok(logInService.register(user));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logIn(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(logInService.logIn(userDTO.getEmail(), userDTO.getPassword()));
    }
}
