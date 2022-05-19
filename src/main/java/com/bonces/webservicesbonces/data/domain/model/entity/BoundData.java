package com.bonces.webservicesbonces.data.domain.model.entity;

import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.shared.domain.model.entity.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "bound_data")
@Inheritance(strategy = InheritanceType.JOINED)
public class BoundData extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double nominalValue;

    @NotNull
    private Double commercialValue;

    @NotNull
    private Double years;

    @NotNull
    private String couponFrequency;

    @NotNull
    private Double daysYear;

    @NotNull
    private String typeInterestRate;

    @NotNull
    private String capitalization;

    @NotNull
    private Double interestRate;

    @NotNull
    private Double annualDiscountRate;

    @NotNull
    private Double incomeTax;

    @NotNull
    private Date issue;

    @NotNull
    private Double gracePeriod;

    @NotNull
    private String termType;

    @NotNull
    private Double timeline;
}