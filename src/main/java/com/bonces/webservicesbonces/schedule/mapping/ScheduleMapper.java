package com.bonces.webservicesbonces.schedule.mapping;

import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.resource.ScheduleResource;
import com.bonces.webservicesbonces.schedule.resource.create.CreateScheduleResource;
import com.bonces.webservicesbonces.schedule.resource.update.UpdateScheduleResource;
import com.bonces.webservicesbonces.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ScheduleMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ScheduleResource toResource(Schedule model) {
        return mapper.map(model, ScheduleResource.class);
    }

    public Schedule toModel(CreateScheduleResource resource) {
        return mapper.map(resource, Schedule.class);
    }

    public Schedule toModel(UpdateScheduleResource resource) {
        return mapper.map(resource, Schedule.class);
    }
}
