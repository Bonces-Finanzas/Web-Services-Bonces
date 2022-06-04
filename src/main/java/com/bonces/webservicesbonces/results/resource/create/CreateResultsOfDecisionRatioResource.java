package com.bonces.webservicesbonces.results.resource.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateResultsOfDecisionRatioResource {

    @NotNull
    private Double duration;

    @NotNull
    private Double convexity;

    @NotNull
    private Double total;

    @NotNull
    private Double modifiedDuration;
}
