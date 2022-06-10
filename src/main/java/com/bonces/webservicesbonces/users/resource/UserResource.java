package com.bonces.webservicesbonces.users.resource;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.shared.resource.AuditModelResource;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class UserResource extends AuditModelResource {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Set<Schedule> schedules;
}
