package com.bonces.webservicesbonces.users.mapping;

import com.bonces.webservicesbonces.shared.mapping.EnhancedModelMapper;
import com.bonces.webservicesbonces.users.domain.model.entity.User;
import com.bonces.webservicesbonces.users.resource.UserResource;
import com.bonces.webservicesbonces.users.resource.create.CreateUserResource;
import com.bonces.webservicesbonces.users.resource.update.UpdateUserResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class UserMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }

    public User toModel(CreateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    public User toModel(UpdateUserResource resource) {
        return mapper.map(resource, User.class);
    }
}
