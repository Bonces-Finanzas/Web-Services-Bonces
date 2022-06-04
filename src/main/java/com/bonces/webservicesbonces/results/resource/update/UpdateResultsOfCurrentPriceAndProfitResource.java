package com.bonces.webservicesbonces.results.resource.update;


import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateResultsOfCurrentPriceAndProfitResource {

    @NotNull
    private Double currentPrice;

    @NotNull
    private Double lostProfit;
}
