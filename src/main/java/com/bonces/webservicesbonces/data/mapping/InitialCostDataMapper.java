package com.bonces.webservicesbonces.data.mapping;

import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;
import com.bonces.webservicesbonces.data.resource.InitialCostDataResource;
import com.bonces.webservicesbonces.data.resource.create.CreateInitialCostDataResource;
import com.bonces.webservicesbonces.data.resource.update.UpdateInitialCostDataResource;
import com.bonces.webservicesbonces.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class InitialCostDataMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public InitialCostDataResource toResource(InitialCostData model) {
        return mapper.map(model, InitialCostDataResource.class);
    }

    public InitialCostData toModel(CreateInitialCostDataResource resource) {
        return mapper.map(resource, InitialCostData.class);
    }

    public InitialCostData toModel(UpdateInitialCostDataResource resource) {
        return mapper.map(resource, InitialCostData.class);
    }
}
