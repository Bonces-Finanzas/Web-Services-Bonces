package com.bonces.webservicesbonces.data.resource.create;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateBoundDataResource {
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
