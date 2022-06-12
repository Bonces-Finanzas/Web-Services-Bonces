package com.bonces.webservicesbonces.results.resource;

import com.bonces.webservicesbonces.shared.resource.AuditModelResource;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class ProfitabilityResultsResource extends AuditModelResource {
    private Long id;
    private double emitterTcea;
    private double emitterTceaWithShield;
    private double bondHolderTrea;
    private double emitterTirTcea;
    private double emitterTirTceaWithShield;
    private double bondholderTirTrea;
}
