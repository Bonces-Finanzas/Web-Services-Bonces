package com.bonces.webservicesbonces.data.domain.service;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;

public interface BoundDataService {
    BoundData createBoundData(Long scheduleId, BoundData request);
    BoundData updateBoundData(Long scheduleId, BoundData request);
}
