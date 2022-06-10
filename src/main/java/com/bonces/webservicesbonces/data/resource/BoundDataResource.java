package com.bonces.webservicesbonces.data.resource;

import com.bonces.webservicesbonces.shared.resource.AuditModelResource;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class BoundDataResource extends AuditModelResource {
    private Long id;
    private Double nominalValue;
    private Double commercialValue;
    private Double years;
    private String couponFrequency;
    private Double daysYear;
    private String typeInterestRate;
    private String capitalization;
    private Double interestRate;
    private Double annualDiscountRate;
    private Double incomeTax;
    private Date issue;
    private Double gracePeriod;
    private String termType;
}
