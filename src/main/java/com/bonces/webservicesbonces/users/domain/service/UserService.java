package com.bonces.webservicesbonces.users.domain.service;

import com.bonces.webservicesbonces.users.domain.model.entity.User;
import com.bonces.webservicesbonces.users.resource.create.CreateUserResource;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User authenticate(String email, String password);
    User register(User request);
    User update(Long userId, User request);
    ResponseEntity<?> delete(Long userId);
}
