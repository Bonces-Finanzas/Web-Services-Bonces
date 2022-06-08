package com.bonces.webservicesbonces.users.resource.create;

import javax.validation.constraints.NotNull;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateUserResource {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
