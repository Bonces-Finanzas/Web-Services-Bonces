package com.bonces.webservicesbonces.schedule.resource.update;

import com.bonces.webservicesbonces.data.resource.update.UpdateBoundDataResource;
import com.bonces.webservicesbonces.data.resource.update.UpdateInitialCostDataResource;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateScheduleResource {
    @NotNull
    private UpdateBoundDataResource updateBoundDataResource;

    @NotNull
    private UpdateInitialCostDataResource updateInitialCostDataResource;
}
