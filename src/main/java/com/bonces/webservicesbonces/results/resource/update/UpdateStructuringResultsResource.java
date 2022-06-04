package com.bonces.webservicesbonces.results.resource.update;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateStructuringResultsResource {
    @NotNull
    private Long couponFrequencyDays;

    @NotNull
    private Long capitalizationDays;

    @NotNull
    private Long periodsPerYear;

    @NotNull
    private Long totalNumberOfPeriods;

    @NotNull
    private Double effectiveAnnualRate;

    @NotNull
    private Double effectiveRate;

    @NotNull
    private Double COK;

    @NotNull
    private Double initialCostsEmitter;

    @NotNull
    private Double initialCostsBondholder;
}
