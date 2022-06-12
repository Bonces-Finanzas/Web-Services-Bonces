package com.bonces.webservicesbonces.data.mapping;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import com.bonces.webservicesbonces.data.resource.BoundDataResource;
import com.bonces.webservicesbonces.data.resource.create.CreateBoundDataResource;
import com.bonces.webservicesbonces.data.resource.update.UpdateBoundDataResource;
import com.bonces.webservicesbonces.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class BoundDataMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public BoundDataResource toResource(BoundData model) {
        return mapper.map(model, BoundDataResource.class);
    }

    public BoundData toModel(CreateBoundDataResource resource) {
        return mapper.map(resource, BoundData.class);
    }

    public BoundData toModel(UpdateBoundDataResource resource) {
        return mapper.map(resource, BoundData.class);
    }
}
