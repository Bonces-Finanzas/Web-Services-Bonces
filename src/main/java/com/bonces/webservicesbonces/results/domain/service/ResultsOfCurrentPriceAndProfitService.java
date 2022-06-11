package com.bonces.webservicesbonces.results.domain.service;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;

public interface ResultsOfCurrentPriceAndProfitService {
    ResultsOfCurrentPriceAndProfit createResultsOfCurrentPriceAndProfit(Long scheduleId, ResultsOfCurrentPriceAndProfit request);
    ResultsOfCurrentPriceAndProfit updateResultsOfCurrentPriceAndProfit(Long scheduleId, ResultsOfCurrentPriceAndProfit request);
}
