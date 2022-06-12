package com.bonces.webservicesbonces.data.resource.create;

import com.bonces.webservicesbonces.data.domain.model.enums.Capitalization;
import com.bonces.webservicesbonces.data.domain.model.enums.CouponFrequency;
import com.bonces.webservicesbonces.data.domain.model.enums.TypeInterestRate;
import com.bonces.webservicesbonces.quota.domain.model.enums.TypeOfGracePeriod;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private int years;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CouponFrequency couponFrequency;

    @NotNull
    private int daysYear;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TypeInterestRate typeInterestRate;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Capitalization capitalization;

    @NotNull
    private Double interestRate;

    @NotNull
    private Double annualDiscountRate;

    @NotNull
    private Double incomeTax;

    @NotNull
    private Date issue;

    @NotNull
    private Long gracePeriod;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TypeOfGracePeriod typeOfGracePeriod;
}
