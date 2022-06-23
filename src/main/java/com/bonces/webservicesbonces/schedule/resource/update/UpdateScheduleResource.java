package com.bonces.webservicesbonces.schedule.resource.update;

import com.bonces.webservicesbonces.data.resource.update.UpdateBoundDataResource;
import com.bonces.webservicesbonces.data.resource.update.UpdateInitialCostDataResource;
import com.bonces.webservicesbonces.schedule.domain.model.enums.CurrencyType;
import com.bonces.webservicesbonces.schedule.domain.model.enums.MethodType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateScheduleResource {
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private MethodType methodType;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CurrencyType currencyType;

    @NotNull
    private UpdateBoundDataResource updateBoundDataResource;

    @NotNull
    private UpdateInitialCostDataResource updateInitialCostDataResource;
}
