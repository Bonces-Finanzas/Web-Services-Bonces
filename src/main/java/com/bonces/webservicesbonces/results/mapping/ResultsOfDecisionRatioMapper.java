package com.bonces.webservicesbonces.results.mapping;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import com.bonces.webservicesbonces.results.resource.ResultsOfDecisionRatioResource;
import com.bonces.webservicesbonces.results.resource.create.CreateResultsOfDecisionRatioResource;
import com.bonces.webservicesbonces.results.resource.update.UpdateResultsOfDecisionRatioResource;
import com.bonces.webservicesbonces.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ResultsOfDecisionRatioMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ResultsOfDecisionRatioResource toResource(ResultsOfDecisionRatio model) {
        return mapper.map(model, ResultsOfDecisionRatioResource.class);
    }

    public ResultsOfDecisionRatio toModel(CreateResultsOfDecisionRatioResource resource) {
        return mapper.map(resource, ResultsOfDecisionRatio.class);
    }

    public ResultsOfDecisionRatio toModel(UpdateResultsOfDecisionRatioResource resource) {
        return mapper.map(resource, ResultsOfDecisionRatio.class);
    }
}
