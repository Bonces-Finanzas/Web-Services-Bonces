package com.bonces.webservicesbonces.results.domain.service;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;

public interface ResultsOfDecisionRatioService {
    ResultsOfDecisionRatio createResultsOfDecisionRatio(Long scheduleId, ResultsOfDecisionRatio request);
    ResultsOfDecisionRatio updateResultsOfDecisionRatio(Long scheduleId, ResultsOfDecisionRatio request);
}
