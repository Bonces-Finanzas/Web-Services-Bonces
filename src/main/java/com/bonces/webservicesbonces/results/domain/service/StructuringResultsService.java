package com.bonces.webservicesbonces.results.domain.service;

import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;

public interface StructuringResultsService {
    StructuringResults createStructuringResults(Long scheduleId, StructuringResults request);
    StructuringResults updateStructuringResults(Long scheduleId, StructuringResults request);
}
