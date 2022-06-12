package com.bonces.webservicesbonces.users.api;

import com.bonces.webservicesbonces.users.domain.service.UserService;
import com.bonces.webservicesbonces.users.mapping.UserMapper;
import com.bonces.webservicesbonces.users.resource.UserResource;
import com.bonces.webservicesbonces.users.resource.create.CreateUserResource;
import com.bonces.webservicesbonces.users.resource.update.UpdateUserResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/sign-in")
    public UserResource authenticateUser(@RequestParam(name = "email") String email,
                                         @RequestParam(name = "password") String password) {
        return userMapper.toResource(userService.authenticate(email, password));
    }

    @PostMapping("/sign-up")
    public UserResource registerUser(@Valid @RequestBody CreateUserResource request) {
        return userMapper.toResource(userService.register(userMapper.toModel(request)));
    }

    @PutMapping("{userId}")
    public UserResource update(@PathVariable Long userId,
                               @Valid @RequestBody UpdateUserResource request) {
        return userMapper.toResource(userService.update(userId, userMapper.toModel(request)));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        return userService.delete(userId);
    }
}
