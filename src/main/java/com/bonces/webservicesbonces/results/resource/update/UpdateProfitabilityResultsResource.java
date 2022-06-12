package com.bonces.webservicesbonces.results.resource.update;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateProfitabilityResultsResource {
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
