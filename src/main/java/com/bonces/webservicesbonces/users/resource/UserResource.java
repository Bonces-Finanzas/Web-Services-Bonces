package com.bonces.webservicesbonces.users.resource;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class UserResource {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
}
