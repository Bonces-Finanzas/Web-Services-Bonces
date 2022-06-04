package com.bonces.webservicesbonces.results.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StructuringResultsResource {

    private Long id;
    private Long couponFrequencyDays;
    private Long capitalizationDays;
    private Long periodsPerYear;
    private Long totalNumberOfPeriods;
    private Double effectiveAnnualRate;
    private Double effectiveRate;
    private Double COK;
    private Double initialCostsEmitter;
    private Double initialCostsBondholder;
}
