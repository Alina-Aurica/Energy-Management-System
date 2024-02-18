package com.example.usermanagementservice.services.implementation;

import com.example.usermanagementservice.controllers.auth.AuthenticationResponse;
import com.example.usermanagementservice.entities.Role;
import com.example.usermanagementservice.entities.Users;
import com.example.usermanagementservice.repositories.UserRepository;
import com.example.usermanagementservice.services.LogInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class LogInServiceImplementation implements LogInService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse register(Users user) {
        var user1 = Users.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
        userRepository.save(user1);
        System.out.println(user1);
        var jwtToken = jwtService.generateToken(user1);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse registerAdmin(Users user) {
        var user1 = Users.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user1);
        var jwtToken = jwtService.generateToken(user1);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse logIn(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            var user = userRepository.findByEmail(email)
                    .orElseThrow();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();

        } catch (Exception exception) {
            log.error("{}", exception);
        }

        return null;
    }

//    @Override
//    public Optional<UserDTO> logOut(String email) {
//        return Optional.empty();
//    }
//
//    @Override
//    public AuthenticationResponse changePassword(Users user, String password) {
//        return null;
//    }
}
