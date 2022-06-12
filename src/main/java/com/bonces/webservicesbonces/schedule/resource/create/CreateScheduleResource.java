package com.bonces.webservicesbonces.schedule.resource.create;

import com.bonces.webservicesbonces.data.resource.create.CreateBoundDataResource;
import com.bonces.webservicesbonces.data.resource.create.CreateInitialCostDataResource;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateScheduleResource {
    @NotNull
    private Date date;

    @NotNull
    private CreateBoundDataResource createBoundDataResource;

    @NotNull
    private CreateInitialCostDataResource createInitialCostDataResource;
}
