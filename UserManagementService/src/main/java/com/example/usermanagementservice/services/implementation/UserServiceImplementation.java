package com.example.usermanagementservice.services.implementation;

import com.example.usermanagementservice.dtos.UserDTO;
import com.example.usermanagementservice.entities.Role;
import com.example.usermanagementservice.entities.Users;
import com.example.usermanagementservice.repositories.UserRepository;
import com.example.usermanagementservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDTO> findById(UUID id) {
        Optional<Users> user = userRepository.findById(id);
        Optional<UserDTO> userDTO = Optional.ofNullable(modelMapper.map(user, UserDTO.class));
        return userDTO;
    }

    @Override
    public Optional<UserDTO> findByFirstName(String firstName) {
        Optional<Users> user = userRepository.findByFirstName(firstName);
        Optional<UserDTO> userDTO = Optional.ofNullable(modelMapper.map(user, UserDTO.class));
        return userDTO;
    }

    @Override
    public Optional<UserDTO> findByLastName(String lastName) {
        Optional<Users> user = userRepository.findByLastName(lastName);
        Optional<UserDTO> userDTO = Optional.ofNullable(modelMapper.map(user, UserDTO.class));
        return userDTO;
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        Optional<UserDTO> userDTO = Optional.ofNullable(modelMapper.map(user, UserDTO.class));
        return userDTO;
    }

    @Override
    public Optional<UserDTO> findByRole(Role role) {
        Optional<Users> user = userRepository.findByRole(role);
        Optional<UserDTO> userDTO = Optional.ofNullable(modelMapper.map(user, UserDTO.class));
        return userDTO;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<Users> users = userRepository.findAll();
        List<UserDTO> usersDTO = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    public List<UserDTO> findAllUsersByFirstName(String firstName) {
        List<Users> users = userRepository.findAllByFirstName(firstName);
        List<UserDTO> usersDTO = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    public List<UserDTO> findAllUsersByLastName(String lastName) {
        List<Users> users = userRepository.findAllByLastName(lastName);
        List<UserDTO> usersDTO = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    public List<UserDTO> findAllUsersByEmail(String email) {
        List<Users> users = userRepository.findAllByEmail(email);
        List<UserDTO> usersDTO = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    public List<UserDTO> findAllUsersByRole(Role role) {
        List<Users> users = userRepository.findAllByRole(role);
        List<UserDTO> usersDTO = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    public Optional<UserDTO> insertUser(Users user) {
        Users userInserted = new Users();
        userInserted.setFirstName(user.getFirstName());
        userInserted.setLastName(user.getLastName());
        userInserted.setEmail(user.getEmail());
        userInserted.setPassword(passwordEncoder.encode(user.getPassword()));
        userInserted.setRole(user.getRole());
        userRepository.save(userInserted);
        Optional<UserDTO> userDTO = Optional.ofNullable(modelMapper.map(userInserted, UserDTO.class));
        return userDTO;
    }

    @Override
    public Optional<UserDTO> updateUserEmail(String firstName, String lastName, String email) {
        Optional<Users> userForUpdate = userRepository.findByFirstNameAndLastName(firstName, lastName);
        userForUpdate.get().setEmail(email);
        userRepository.save(userForUpdate.get());
        Optional<UserDTO> userDTO = Optional.ofNullable(modelMapper.map(userForUpdate, UserDTO.class));
        return userDTO;
    }

    @Override
    public Optional<UserDTO> deleteUserByEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        userRepository.delete(user.get());
        Optional<UserDTO> userDTO = Optional.ofNullable(modelMapper.map(user, UserDTO.class));
        return userDTO;
    }

    @Override
    public List<String> findAllEmailsByRole(Role role) {
        List<Users> users = userRepository.findAllByRole(role);
        List<String> emails = new ArrayList<String>();
        for(Users u: users){
            emails.add(u.getEmail());
        }
        return emails;
    }
}
