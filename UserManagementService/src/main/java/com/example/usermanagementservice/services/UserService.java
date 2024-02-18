package com.example.usermanagementservice.services;

import com.example.usermanagementservice.dtos.UserDTO;
import com.example.usermanagementservice.entities.Role;
import com.example.usermanagementservice.entities.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface UserService {
    Optional<UserDTO> findById(UUID id);
    Optional<UserDTO> findByFirstName(String firstName);
    Optional<UserDTO> findByLastName(String lastName);
    Optional<UserDTO> findByEmail(String email);
    Optional<UserDTO> findByRole(Role role);
    List<UserDTO> findAllUsers();
    List<UserDTO> findAllUsersByFirstName(String firstName);
    List<UserDTO> findAllUsersByLastName(String lastName);
    List<UserDTO> findAllUsersByEmail(String email);
    List<UserDTO> findAllUsersByRole(Role role);
    Optional<UserDTO> insertUser(Users user);
    Optional<UserDTO> updateUserEmail(String firstName, String lastName, String email);
    Optional<UserDTO> deleteUserByEmail(String email);
    List<String> findAllEmailsByRole(Role role);

}
