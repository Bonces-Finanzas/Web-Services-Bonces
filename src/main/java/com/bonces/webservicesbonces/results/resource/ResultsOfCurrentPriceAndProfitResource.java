package com.bonces.webservicesbonces.results.resource;

import com.bonces.webservicesbonces.shared.resource.AuditModelResource;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResultsOfCurrentPriceAndProfitResource extends AuditModelResource {
    private Long id;
    private Double currentPrice;
    private Double lostProfit;
}
