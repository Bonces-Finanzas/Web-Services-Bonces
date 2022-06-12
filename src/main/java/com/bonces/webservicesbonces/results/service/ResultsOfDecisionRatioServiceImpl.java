package com.bonces.webservicesbonces.results.service;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import com.bonces.webservicesbonces.results.domain.persistence.ResultsOfDecisionRatioRepository;
import com.bonces.webservicesbonces.results.domain.service.ResultsOfDecisionRatioService;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ResultsOfDecisionRatioServiceImpl implements ResultsOfDecisionRatioService {
    private static final String ENTITY = "ResultsOfDecisionRatio";
    private final ResultsOfDecisionRatioRepository resultsOfDecisionRatioRepository;
    private final ScheduleRepository scheduleRepository;
    private final Validator validator;

    public ResultsOfDecisionRatioServiceImpl(ResultsOfDecisionRatioRepository resultsOfDecisionRatioRepository, ScheduleRepository scheduleRepository, Validator validator) {
        this.resultsOfDecisionRatioRepository = resultsOfDecisionRatioRepository;
        this.scheduleRepository = scheduleRepository;
        this.validator = validator;
    }

    @Override
    public ResultsOfDecisionRatio createResultsOfDecisionRatio(Long scheduleId, ResultsOfDecisionRatio request) {
        Set<ConstraintViolation<ResultsOfDecisionRatio>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule", scheduleId));;

        request.setSchedule(schedule);

        return resultsOfDecisionRatioRepository.save(request);
    }

    @Override
    public ResultsOfDecisionRatio updateResultsOfDecisionRatio(Long scheduleId, ResultsOfDecisionRatio request) {
        Set<ConstraintViolation<ResultsOfDecisionRatio>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return resultsOfDecisionRatioRepository.findByScheduleId(scheduleId).map(resultsOfDecisionRatio ->
                resultsOfDecisionRatioRepository.save(resultsOfDecisionRatio.withDuration(request.getDuration())
                        .withConvexity(request.getConvexity())
                        .withTotal(request.getTotal())
                        .withModifiedDuration(request.getModifiedDuration()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }
}
