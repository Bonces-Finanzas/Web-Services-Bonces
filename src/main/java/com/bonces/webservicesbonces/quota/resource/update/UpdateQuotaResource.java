package com.bonces.webservicesbonces.quota.resource.update;

import com.bonces.webservicesbonces.quota.domain.model.enums.TypeOfGracePeriod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UpdateQuotaResource {
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledDate;

    @NotNull
    private int numberOfQuota;

    @NotNull
    private Double inflation;

    @NotNull
    private Double periodInflation;

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
