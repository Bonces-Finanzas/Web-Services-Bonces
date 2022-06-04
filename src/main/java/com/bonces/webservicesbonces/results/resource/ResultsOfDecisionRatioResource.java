package com.bonces.webservicesbonces.results.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResultsOfDecisionRatioResource {

    private Long id;

    @NotNull
    private Double duration;

    @NotNull
    private Double convexity;

    @NotNull
    private Double total;

    @NotNull
    private Double modifiedDuration;
}
