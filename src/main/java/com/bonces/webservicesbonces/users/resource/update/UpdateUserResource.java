package com.bonces.webservicesbonces.users.resource.update;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserResource {
    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String email;
}
