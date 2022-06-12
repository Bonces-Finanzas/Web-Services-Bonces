package com.bonces.webservicesbonces.results.service;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;
import com.bonces.webservicesbonces.results.domain.persistence.ResultsOfCurrentPriceAndProfitRepository;
import com.bonces.webservicesbonces.results.domain.service.ResultsOfCurrentPriceAndProfitService;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ResultsOfCurrentPriceAndProfitServiceImpl implements ResultsOfCurrentPriceAndProfitService {
    private static final String ENTITY = "ResultsOfCurrentPriceAndProfit";
    private final ResultsOfCurrentPriceAndProfitRepository resultsOfCurrentPriceAndProfitRepository;
    private final ScheduleRepository scheduleRepository;
    private final Validator validator;

    public ResultsOfCurrentPriceAndProfitServiceImpl(ResultsOfCurrentPriceAndProfitRepository resultsOfCurrentPriceAndProfitRepository, ScheduleRepository scheduleRepository, Validator validator) {
        this.resultsOfCurrentPriceAndProfitRepository = resultsOfCurrentPriceAndProfitRepository;
        this.scheduleRepository = scheduleRepository;
        this.validator = validator;
    }

    @Override
    public ResultsOfCurrentPriceAndProfit createResultsOfCurrentPriceAndProfit(Long scheduleId, ResultsOfCurrentPriceAndProfit request) {
        Set<ConstraintViolation<ResultsOfCurrentPriceAndProfit>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule", scheduleId));;

        request.setSchedule(schedule);

        return resultsOfCurrentPriceAndProfitRepository.save(request);
    }

    @Override
    public ResultsOfCurrentPriceAndProfit updateResultsOfCurrentPriceAndProfit(Long scheduleId, ResultsOfCurrentPriceAndProfit request) {
        Set<ConstraintViolation<ResultsOfCurrentPriceAndProfit>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return resultsOfCurrentPriceAndProfitRepository.findByScheduleId(scheduleId).map(resultsOfCurrentPriceAndProfit ->
                resultsOfCurrentPriceAndProfitRepository.save(resultsOfCurrentPriceAndProfit.withCurrentPrice(request.getCurrentPrice())
                        .withLostProfit(request.getLostProfit()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }
}
