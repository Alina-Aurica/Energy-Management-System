package com.example.usermanagementservice.services;

import com.example.usermanagementservice.controllers.auth.AuthenticationResponse;
import com.example.usermanagementservice.entities.Users;
import org.springframework.stereotype.Component;

@Component
public interface LogInService {
    AuthenticationResponse register(Users user);
    AuthenticationResponse registerAdmin(Users user);
    AuthenticationResponse logIn(String email, String password);
//    Optional<UserDTO> logOut(String email);
//    AuthenticationResponse changePassword(Users user, String password);
}
