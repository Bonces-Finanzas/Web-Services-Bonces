package com.bonces.webservicesbonces.data.resource.create;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateInitialCostDataResource {
    @NotNull
    private Double premium;

    @NotNull
    private Double structuring;

    @NotNull
    private Double collocation;

    @NotNull
    private Double floatation;

    @NotNull
    private Double cavali;

}
