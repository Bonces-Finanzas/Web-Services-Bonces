package com.bonces.webservicesbonces.users.domain.persistence;

import com.bonces.webservicesbonces.users.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Boolean existsByNameAndLastName(String name, String lastName);
    Boolean existsByEmail(String email);
}
