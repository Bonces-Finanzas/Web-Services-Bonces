package com.bonces.webservicesbonces.results.resource.create;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateProfitabilityResultsResource {
    @NotNull
    private double emitterTcea;

    @NotNull
    private double emitterTceaWithShield;

    @NotNull
    private double bondHolderTrea ;

    @NotNull
    private double emitterTirTcea;

    @NotNull
    private double emitterTirTceaWithShield;

    @NotNull
    private double bondholderTirTrea;
}
