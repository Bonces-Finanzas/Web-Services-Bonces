package com.bonces.webservicesbonces.results.mapping;

import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;
import com.bonces.webservicesbonces.results.resource.StructuringResultsResource;
import com.bonces.webservicesbonces.results.resource.create.CreateStructuringResultsResource;
import com.bonces.webservicesbonces.results.resource.update.UpdateStructuringResultsResource;
import com.bonces.webservicesbonces.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class StructuringResultsMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public StructuringResultsResource toResource(StructuringResults model) {
        return mapper.map(model, StructuringResultsResource.class);
    }

    public StructuringResults toModel(CreateStructuringResultsResource resource) {
        return mapper.map(resource, StructuringResults.class);
    }

    public StructuringResults toModel(UpdateStructuringResultsResource resource) {
        return mapper.map(resource, StructuringResults.class);
    }
}
