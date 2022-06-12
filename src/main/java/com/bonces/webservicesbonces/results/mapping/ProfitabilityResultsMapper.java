package com.bonces.webservicesbonces.results.mapping;

import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;
import com.bonces.webservicesbonces.results.resource.ProfitabilityResultsResource;
import com.bonces.webservicesbonces.results.resource.create.CreateProfitabilityResultsResource;
import com.bonces.webservicesbonces.results.resource.update.UpdateProfitabilityResultsResource;
import com.bonces.webservicesbonces.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ProfitabilityResultsMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ProfitabilityResultsResource toResource(ProfitabilityResults model) {
        return mapper.map(model, ProfitabilityResultsResource.class);
    }

    public ProfitabilityResults toModel(CreateProfitabilityResultsResource resource) {
        return mapper.map(resource, ProfitabilityResults.class);
    }

    public ProfitabilityResults toModel(UpdateProfitabilityResultsResource resource) {
        return mapper.map(resource, ProfitabilityResults.class);
    }
}
