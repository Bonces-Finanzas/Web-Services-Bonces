package com.bonces.webservicesbonces.results.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResultsOfCurrentPriceAndProfitResource {

    private Long id;

    @NotNull
    private Double currentPrice;

    @NotNull
    private Double lostProfit;
}
