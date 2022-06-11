package com.bonces.webservicesbonces.data.domain.service;

import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;

public interface InitialCostDataService {
    InitialCostData createInitialCostData(Long scheduleId, InitialCostData request);
    InitialCostData updateInitialCostData(Long scheduleId, InitialCostData request);
}
