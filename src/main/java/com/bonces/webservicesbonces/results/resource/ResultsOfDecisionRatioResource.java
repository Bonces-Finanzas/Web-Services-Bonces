package com.bonces.webservicesbonces.results.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResultsOfDecisionRatioResource {

    private Long id;
    private Double duration;
    private Double convexity;
    private Double total;
    private Double modifiedDuration;
}
