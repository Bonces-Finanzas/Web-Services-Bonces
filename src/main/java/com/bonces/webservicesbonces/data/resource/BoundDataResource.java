package com.bonces.webservicesbonces.data.resource;

import com.bonces.webservicesbonces.data.domain.model.enums.Capitalization;
import com.bonces.webservicesbonces.data.domain.model.enums.CouponFrequency;
import com.bonces.webservicesbonces.data.domain.model.enums.TypeInterestRate;
import com.bonces.webservicesbonces.quota.domain.model.enums.TypeOfGracePeriod;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class BoundDataResource {
    private Long id;
    private Double nominalValue;
    private Double commercialValue;
    private int years;
    private CouponFrequency couponFrequency;
    private int daysYear;
    private TypeInterestRate typeInterestRate;
    private Capitalization capitalization;
    private Double interestRate;
    private Double annualDiscountRate;
    private Double incomeTax;
    private Date issue;
    private Long gracePeriod;
    private TypeOfGracePeriod typeOfGracePeriod;
}
