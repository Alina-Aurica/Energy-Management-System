package com.example.usermanagementservice.controllers;

import com.example.usermanagementservice.dtos.UserDTO;
import com.example.usermanagementservice.entities.Role;
import com.example.usermanagementservice.entities.Users;
import com.example.usermanagementservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findById/{id}")
    public ResponseEntity getUserById(@PathVariable UUID id){
        Optional<UserDTO> userDTO = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/findByFirstName/{firstName}")
    public ResponseEntity getUserByFirstName(@PathVariable String firstName){
        Optional<UserDTO> userDTO = userService.findByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/findByLastName/{lastName}")
    public ResponseEntity getUserByLastName(@PathVariable String lastName){
        Optional<UserDTO> userDTO = userService.findByLastName(lastName);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        Optional<UserDTO> userDTO = userService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/findByRole/{role}")
    public ResponseEntity getUserByName(@PathVariable Role role){
        Optional<UserDTO> userDTO = userService.findByRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity getAllUsers(){
        List<UserDTO> usersDTO = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }

    @GetMapping("/findAllByFirstName/{firstName}")
    public ResponseEntity getAllUsersByFirstName(@PathVariable String firstName){
        List<UserDTO> usersDTO = userService.findAllUsersByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }

    @GetMapping("/findAllByLastName/{lastName}")
    public ResponseEntity getAllUsersByLastName(@PathVariable String lastName){
        List<UserDTO> usersDTO = userService.findAllUsersByLastName(lastName);
        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }

    @GetMapping("/findAllByEmail/{email}")
    public ResponseEntity getAllUsersByEmail(@PathVariable String email){
        List<UserDTO> usersDTO = userService.findAllUsersByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }

    @GetMapping("/findAllByRole/{role}")
    public ResponseEntity getAllUsersByRole(@PathVariable Role role){
        List<UserDTO> usersDTO = userService.findAllUsersByRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
    }

    @PostMapping("/insert")
    public ResponseEntity<Optional<UserDTO>> insert(@RequestBody Users user){
        Optional<UserDTO> userDTO = userService.insertUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PutMapping("/updateEmail/{firstName}/{lastName}/{email}")
    public ResponseEntity<Optional<UserDTO>> updateUserEmail(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String email){
        Optional<UserDTO> userDTO = userService.updateUserEmail(firstName, lastName, email);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<Optional<UserDTO>> deleteUserByEmail(@PathVariable String email){
        Optional<UserDTO> userDTO = userService.deleteUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/findAllEmailsByRole/{role}")
    public ResponseEntity getEmailsByRole(@PathVariable Role role){
        List<String> emails = userService.findAllEmailsByRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(emails);
    }
}
