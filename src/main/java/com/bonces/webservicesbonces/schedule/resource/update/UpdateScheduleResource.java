package com.bonces.webservicesbonces.schedule.resource.update;

import com.bonces.webservicesbonces.data.resource.update.UpdateBoundDataResource;
import com.bonces.webservicesbonces.data.resource.update.UpdateInitialCostDataResource;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UpdateScheduleResource {
    @NotNull
    @NotBlank
    private Date date;

    @NotNull
    private UpdateBoundDataResource updateBoundDataResource;

    @NotNull
    private UpdateInitialCostDataResource updateInitialCostDataResource;
}
