package com.bonces.webservicesbonces.schedule.service;

import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.schedule.domain.service.ScheduleService;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final String ENTITY = "Schedule";
    private final ScheduleRepository scheduleRepository;
    //private final UserRepository userRepository;
    private final Validator validator;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, Validator validator) {
        this.scheduleRepository = scheduleRepository;
        this.validator = validator;
    }

    @Override
    public Schedule getById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }

    @Override
    public Schedule create(Long userId, Schedule request) {
        Set<ConstraintViolation<Schedule>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        FrenchAlgorithm frenchAlgorithm = new FrenchAlgorithm(request.getBoundData(), request.getInitialCostData(), request.getDate());

        /*BoundData boundData = frenchAlgorithm.boundData;
        InitialCostData initialCostData = frenchAlgorithm.initialCostData;
        StructuringResults structuringResults = frenchAlgorithm.structuringResults;
        ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit = frenchAlgorithm.resultsOfCurrentPriceAndProfit;
        ResultsOfDecisionRatio resultsOfDecisionRatio = frenchAlgorithm.resultsOfDecisionRatio;
        ProfitabilityResults profitabilityResults = frenchAlgorithm.profitabilityResults;
        Set<Quota> quotas = frenchAlgorithm.quotas;*/

        //User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));

        //request.setUser(user);
        request.setBoundData(frenchAlgorithm.boundData);
        request.setInitialCostData(frenchAlgorithm.initialCostData);
        request.setStructuringResults(frenchAlgorithm.structuringResults);
        request.setResultsOfCurrentPriceAndProfit(frenchAlgorithm.resultsOfCurrentPriceAndProfit);
        request.setResultsOfDecisionRatio(frenchAlgorithm.resultsOfDecisionRatio);
        request.setProfitabilityResults(frenchAlgorithm.profitabilityResults);
        request.setQuotas(frenchAlgorithm.quotas);

        return scheduleRepository.save(request);
    }

    @Override
    public Schedule update(Long scheduleId, Schedule request) {
        Set<ConstraintViolation<Schedule>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return scheduleRepository.findById(scheduleId).map(schedule -> {
            FrenchAlgorithm frenchAlgorithm = new FrenchAlgorithm(request.getBoundData(), request.getInitialCostData(), request.getDate());

            /*BoundData boundData = frenchAlgorithm.boundData;
            InitialCostData initialCostData = frenchAlgorithm.initialCostData;
            StructuringResults structuringResults = frenchAlgorithm.structuringResults;
            ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit = frenchAlgorithm.resultsOfCurrentPriceAndProfit;
            ResultsOfDecisionRatio resultsOfDecisionRatio = frenchAlgorithm.resultsOfDecisionRatio;
            ProfitabilityResults profitabilityResults = frenchAlgorithm.profitabilityResults;
            Set<Quota> quotas = frenchAlgorithm.quotas;*/

            return scheduleRepository.save(schedule.withUser(request.getUser())
                    .withBoundData(frenchAlgorithm.boundData)
                    .withInitialCostData(frenchAlgorithm.initialCostData)
                    .withStructuringResults(frenchAlgorithm.structuringResults)
                    .withResultsOfCurrentPriceAndProfit(frenchAlgorithm.resultsOfCurrentPriceAndProfit)
                    .withResultsOfDecisionRatio(frenchAlgorithm.resultsOfDecisionRatio)
                    .withProfitabilityResults(frenchAlgorithm.profitabilityResults)
                    .withDate(request.getDate())
                    .withQuotas(frenchAlgorithm.quotas));
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }

    @Override
    public ResponseEntity<?> delete(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).map(schedule -> {
            scheduleRepository.delete(schedule);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }
}
