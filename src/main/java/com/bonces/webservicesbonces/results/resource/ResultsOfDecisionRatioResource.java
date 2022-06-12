package com.bonces.webservicesbonces.results.resource;

import com.bonces.webservicesbonces.shared.resource.AuditModelResource;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResultsOfDecisionRatioResource extends AuditModelResource {
    private Long id;
    private Double duration;
    private Double convexity;
    private Double total;
    private Double modifiedDuration;
}
