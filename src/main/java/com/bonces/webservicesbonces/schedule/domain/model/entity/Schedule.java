package com.bonces.webservicesbonces.schedule.domain.model.entity;

import com.bonces.webservicesbonces.shared.domain.model.entity.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "schedules")
@Inheritance(strategy = InheritanceType.JOINED)
public class Schedule extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
