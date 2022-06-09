package com.bonces.webservicesbonces.results.resource;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class ProfitabilityResultsResource {
    private Long id;
    private double emitterTcea;
    private double emitterTceaWithShield;
    private double bondHolderTrea;
    private double emitterTirTcea;
    private double emitterTirTceaWithShield;
    private double bondholderTirTrea;
}
