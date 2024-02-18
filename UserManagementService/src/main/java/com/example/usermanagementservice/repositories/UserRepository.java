package com.example.usermanagementservice.repositories;

import com.example.usermanagementservice.entities.Role;
import com.example.usermanagementservice.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByFirstName(String firstName);
    Optional<Users> findByLastName(String lastName);
    Optional<Users> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByRole(Role role);
    List<Users> findAll();
    List<Users> findAllByFirstName(String firstName);
    List<Users> findAllByLastName(String lastName);
    List<Users> findAllByEmail(String email);
    List<Users> findAllByRole(Role role);


}
