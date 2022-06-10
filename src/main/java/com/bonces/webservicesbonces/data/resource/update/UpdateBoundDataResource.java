package com.bonces.webservicesbonces.data.resource.update;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UpdateBoundDataResource {
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
}
