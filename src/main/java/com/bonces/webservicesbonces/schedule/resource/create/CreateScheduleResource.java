package com.bonces.webservicesbonces.schedule.resource.create;

import com.bonces.webservicesbonces.data.resource.create.CreateBoundDataResource;
import com.bonces.webservicesbonces.data.resource.create.CreateInitialCostDataResource;
import com.bonces.webservicesbonces.schedule.domain.model.enums.CurrencyType;
import com.bonces.webservicesbonces.schedule.domain.model.enums.MethodType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class CreateScheduleResource {
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private MethodType methodType;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CurrencyType currencyType;

    @NotNull
    private CreateBoundDataResource createBoundDataResource;

    @NotNull
    private CreateInitialCostDataResource createInitialCostDataResource;
}
