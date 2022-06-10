package com.bonces.webservicesbonces.data.resource.update;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UpdateInitialCostDataResource {
    @NotNull
    private Double premium;

    @NotNull
    private Double structuring;

    @NotNull
    private Double collocation;

    @NotNull
    private String floatation;

    @NotNull
    private Double cavali;
}
