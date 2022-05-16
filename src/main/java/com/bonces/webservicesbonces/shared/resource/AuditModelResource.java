package com.bonces.webservicesbonces.shared.resource;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class AuditModelResource {
    public Date createdAt;
    public Date updatedAt;
}