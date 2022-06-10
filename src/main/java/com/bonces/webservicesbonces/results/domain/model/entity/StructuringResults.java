package com.bonces.webservicesbonces.results.domain.model.entity;

import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.shared.domain.model.entity.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name="StructuringResults")
@Inheritance(strategy = InheritanceType.JOINED)
public class StructuringResults  extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private long couponFrequencyDays;

    @NotNull
    private long capitalizationDays;

    @NotNull
    private long periodsPerYear;

    @NotNull
    private long totalNumberOfPeriods;

    @NotNull
    private double effectiveAnnualRate;

    @NotNull
    private double effectiveRate;

    @NotNull
    private double COK;

    @NotNull
    private double initialCostsEmitter;

    @NotNull
    private double initialCostsBondholder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schedule_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Schedule schedule;
}











