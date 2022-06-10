package com.bonces.webservicesbonces.users.resource;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import lombok.*;

import java.util.Set;

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
    private Set<Schedule> schedules;
}
