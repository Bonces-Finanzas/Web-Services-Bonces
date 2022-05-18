package com.bonces.webservicesbonces.quota.domain.model.entity;

import com.bonces.webservicesbonces.shared.domain.model.entity.AuditModel;
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
@Table(name = "quotas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Quota extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledDate;

    @NotNull
    private Double annualInflation;

    @NotNull
    private Double semiAnnualInflation;

    @NotNull
    private Double gracePeriod;

    @NotNull
    private Double bond;

    @NotNull
    private Double indexedBond;

    @NotNull
    private Double coupon;

    @NotNull
    private Double quota;

    @NotNull
    private Double amortization;

    @NotNull
    private Double premium;

    @NotNull
    private Double shield;

    @NotNull
    private Double emitterStream;

    @NotNull
    private Double emitterFlowWithShield;

    @NotNull
    private Double boundHolderFlow;

    @NotNull
    private Double currentFlowPerPeriod;

    @NotNull
    private Double convexityFactor;
}
