package com.bonces.webservicesbonces.schedule.resource.create;

import com.bonces.webservicesbonces.data.resource.create.CreateBoundDataResource;
import com.bonces.webservicesbonces.data.resource.create.CreateInitialCostDataResource;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateScheduleResource {
    @NotNull
    private CreateBoundDataResource createBoundDataResource;

    @NotNull
    private CreateInitialCostDataResource createInitialCostDataResource;
}
