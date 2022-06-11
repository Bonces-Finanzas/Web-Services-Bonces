package com.bonces.webservicesbonces.quota.resource.create;

import com.bonces.webservicesbonces.quota.domain.model.enums.TypeOfGracePeriod;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateQuotaResource {
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledDate;

    @NotNull
    private int numberOfQuota;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TypeOfGracePeriod typeOfGracePeriod;

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
    private Double currentFlow;

    @NotNull
    private Double currentFlowPerPeriod;

    @NotNull
    private Double convexityFactor;
}
