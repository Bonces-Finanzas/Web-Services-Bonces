package com.bonces.webservicesbonces.quota.resource;

import com.bonces.webservicesbonces.quota.domain.model.enums.TypeOfGracePeriod;
import com.bonces.webservicesbonces.shared.resource.AuditModelResource;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class QuotaResource extends AuditModelResource {
    private Long id;
    private int numberOfQuota;
    private Date scheduledDate;
    private Double inflation;
    private Double periodInflation;
    private TypeOfGracePeriod typeOfGracePeriod;
    private Double bond;
    private Double indexedBond;
    private Double coupon;
    private Double quota;
    private Double amortization;
    private Double premium;
    private Double shield;
    private Double emitterStream;
    private Double emitterFlowWithShield;
    private Double boundHolderFlow;
    private Double currentFlow;
    private Double currentFlowPerPeriod;
    private Double convexityFactor;
}
