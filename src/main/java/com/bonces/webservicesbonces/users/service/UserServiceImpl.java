package com.bonces.webservicesbonces.users.service;

import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import com.bonces.webservicesbonces.users.domain.model.entity.User;
import com.bonces.webservicesbonces.users.domain.persistence.UserRepository;
import com.bonces.webservicesbonces.users.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final String ENTITY = "User";
    private final UserRepository userRepository;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public User authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ResourceValidationException(ENTITY, "%s %s".formatted(email, password)));
    }

    @Override
    public User register(User request) {
        Set<ConstraintViolation<User>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (userRepository.existsByEmail(request.getEmail()))
            throw new ResourceValidationException("There is already a User with the email: %s".formatted(request.getEmail()));

        if (userRepository.existsByNameAndLastName(request.getName(), request.getLastName()))
            throw new ResourceValidationException("There is already a User with the name and lastname: %s %s".formatted(request.getName(), request.getLastName()));

        return userRepository.save(request);
    }

    @Override
    public User update(Long userId, User request) {
        User auxUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));

        request.setPassword(auxUser.getPassword());

        Set<ConstraintViolation<User>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(user ->
                userRepository.save(user.withName(request.getName())
                        .withLastName(request.getLastName())
                        .withEmail(request.getEmail())
                        .withPassword(request.getPassword()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
