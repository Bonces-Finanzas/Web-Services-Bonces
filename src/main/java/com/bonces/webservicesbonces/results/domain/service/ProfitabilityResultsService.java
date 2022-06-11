package com.bonces.webservicesbonces.results.domain.service;

import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;

public interface ProfitabilityResultsService {
    ProfitabilityResults createProfitabilityResults(Long scheduleId, ProfitabilityResults request);
    ProfitabilityResults updateProfitabilityResults(Long scheduleId, ProfitabilityResults request);
}
