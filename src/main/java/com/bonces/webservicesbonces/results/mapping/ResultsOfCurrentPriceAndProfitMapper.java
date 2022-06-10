package com.bonces.webservicesbonces.results.mapping;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;
import com.bonces.webservicesbonces.results.resource.ResultsOfCurrentPriceAndProfitResource;
import com.bonces.webservicesbonces.results.resource.create.CreateResultsOfCurrentPriceAndProfitResource;
import com.bonces.webservicesbonces.results.resource.update.UpdateResultsOfCurrentPriceAndProfitResource;
import com.bonces.webservicesbonces.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ResultsOfCurrentPriceAndProfitMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ResultsOfCurrentPriceAndProfitResource toResource(ResultsOfCurrentPriceAndProfit model) {
        return mapper.map(model, ResultsOfCurrentPriceAndProfitResource.class);
    }

    public ResultsOfCurrentPriceAndProfit toModel(CreateResultsOfCurrentPriceAndProfitResource resource) {
        return mapper.map(resource, ResultsOfCurrentPriceAndProfit.class);
    }

    public ResultsOfCurrentPriceAndProfit toModel(UpdateResultsOfCurrentPriceAndProfitResource resource) {
        return mapper.map(resource, ResultsOfCurrentPriceAndProfit.class);
    }
}
