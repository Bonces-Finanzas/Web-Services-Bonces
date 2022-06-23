package com.bonces.webservicesbonces.schedule.service;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;
import com.bonces.webservicesbonces.data.domain.service.BoundDataService;
import com.bonces.webservicesbonces.data.domain.service.InitialCostDataService;
import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;
import com.bonces.webservicesbonces.quota.domain.service.QuotaService;
import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;
import com.bonces.webservicesbonces.results.domain.service.ProfitabilityResultsService;
import com.bonces.webservicesbonces.results.domain.service.ResultsOfCurrentPriceAndProfitService;
import com.bonces.webservicesbonces.results.domain.service.ResultsOfDecisionRatioService;
import com.bonces.webservicesbonces.results.domain.service.StructuringResultsService;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.schedule.domain.service.ScheduleService;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import com.bonces.webservicesbonces.users.domain.model.entity.User;
import com.bonces.webservicesbonces.users.domain.persistence.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final String ENTITY = "Schedule";
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    private final BoundDataService boundDataService;
    private final InitialCostDataService initialCostDataService;
    private final StructuringResultsService structuringResultsService;
    private final ResultsOfCurrentPriceAndProfitService resultsOfCurrentPriceAndProfitService;
    private final ResultsOfDecisionRatioService resultsOfDecisionRatioService;
    private final ProfitabilityResultsService profitabilityResultsService;
    private final QuotaService quotaService;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository,
                               Validator validator, BoundDataService boundDataService, InitialCostDataService initialCostDataService,
                               StructuringResultsService structuringResultsService,
                               ResultsOfCurrentPriceAndProfitService resultsOfCurrentPriceAndProfitService,
                               ResultsOfDecisionRatioService resultsOfDecisionRatioService,
                               ProfitabilityResultsService profitabilityResultsService, QuotaService quotaService) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.validator = validator;

        this.boundDataService = boundDataService;
        this.initialCostDataService = initialCostDataService;
        this.structuringResultsService = structuringResultsService;
        this.resultsOfCurrentPriceAndProfitService = resultsOfCurrentPriceAndProfitService;
        this.resultsOfDecisionRatioService = resultsOfDecisionRatioService;
        this.profitabilityResultsService = profitabilityResultsService;
        this.quotaService = quotaService;
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

        PaymentPlanCalculation paymentPlanCalculation = new PaymentPlanCalculation(request.getBoundData(), request.getInitialCostData(), request.getMethodType());

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));

        Schedule schedule = new Schedule();
        schedule = scheduleRepository.save(schedule.withUser(user).withMethodType(request.getMethodType()).withCurrencyType(request.getCurrencyType()));

        BoundData boundData = boundDataService.createBoundData(schedule.getId(), paymentPlanCalculation.boundData);
        InitialCostData initialCostData = initialCostDataService.createInitialCostData(schedule.getId(), paymentPlanCalculation.initialCostData);
        StructuringResults structuringResults = structuringResultsService.createStructuringResults(schedule.getId(), paymentPlanCalculation.structuringResults);
        ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit = resultsOfCurrentPriceAndProfitService.createResultsOfCurrentPriceAndProfit(schedule.getId(), paymentPlanCalculation.resultsOfCurrentPriceAndProfit);
        ResultsOfDecisionRatio resultsOfDecisionRatio = resultsOfDecisionRatioService.createResultsOfDecisionRatio(schedule.getId(), paymentPlanCalculation.resultsOfDecisionRatio);
        ProfitabilityResults profitabilityResults = profitabilityResultsService.createProfitabilityResults(schedule.getId(), paymentPlanCalculation.profitabilityResults);
        Set<Quota> quotas = quotaService.createQuotas(schedule.getId(), paymentPlanCalculation.quotas);

        schedule.setBoundData(boundData);
        schedule.setInitialCostData(initialCostData);
        schedule.setStructuringResults(structuringResults);
        schedule.setResultsOfCurrentPriceAndProfit(resultsOfCurrentPriceAndProfit);
        schedule.setResultsOfDecisionRatio(resultsOfDecisionRatio);
        schedule.setProfitabilityResults(profitabilityResults);
        schedule.setQuotas(quotas);

        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule update(Long scheduleId, Schedule request) {
        Set<ConstraintViolation<Schedule>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return scheduleRepository.findById(scheduleId).map(schedule -> {
            PaymentPlanCalculation paymentPlanCalculation = new PaymentPlanCalculation(request.getBoundData(), request.getInitialCostData(), request.getMethodType());

            BoundData boundData = boundDataService.updateBoundData(schedule.getId(), paymentPlanCalculation.boundData);
            InitialCostData initialCostData = initialCostDataService.updateInitialCostData(schedule.getId(), paymentPlanCalculation.initialCostData);
            StructuringResults structuringResults = structuringResultsService.updateStructuringResults(schedule.getId(), paymentPlanCalculation.structuringResults);
            ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit = resultsOfCurrentPriceAndProfitService.updateResultsOfCurrentPriceAndProfit(schedule.getId(), paymentPlanCalculation.resultsOfCurrentPriceAndProfit);
            ResultsOfDecisionRatio resultsOfDecisionRatio = resultsOfDecisionRatioService.updateResultsOfDecisionRatio(schedule.getId(), paymentPlanCalculation.resultsOfDecisionRatio);
            ProfitabilityResults profitabilityResults = profitabilityResultsService.updateProfitabilityResults(schedule.getId(), paymentPlanCalculation.profitabilityResults);
            Set<Quota> quotas = quotaService.updateQuotas(schedule.getId(), paymentPlanCalculation.quotas);

            schedule.setMethodType(request.getMethodType());
            schedule.setCurrencyType(request.getCurrencyType());
            schedule.setBoundData(boundData);
            schedule.setInitialCostData(initialCostData);
            schedule.setStructuringResults(structuringResults);
            schedule.setResultsOfCurrentPriceAndProfit(resultsOfCurrentPriceAndProfit);
            schedule.setResultsOfDecisionRatio(resultsOfDecisionRatio);
            schedule.setProfitabilityResults(profitabilityResults);
            schedule.getQuotas().addAll(quotas);

            return scheduleRepository.save(schedule);
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
