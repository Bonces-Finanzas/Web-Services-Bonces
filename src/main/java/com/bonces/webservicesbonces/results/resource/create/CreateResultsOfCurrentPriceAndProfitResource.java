package com.bonces.webservicesbonces.results.resource.create;

import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateResultsOfCurrentPriceAndProfitResource {

    @NotNull
    private Double currentPrice;

    @NotNull
    private Double lostProfit;
}
