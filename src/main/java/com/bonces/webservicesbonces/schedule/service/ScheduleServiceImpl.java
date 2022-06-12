package com.bonces.webservicesbonces.schedule.service;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;
import com.bonces.webservicesbonces.data.domain.persistence.BoundDataRepository;
import com.bonces.webservicesbonces.data.domain.persistence.InitialCostDataRepository;
import com.bonces.webservicesbonces.data.domain.service.BoundDataService;
import com.bonces.webservicesbonces.data.domain.service.InitialCostDataService;
import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;
import com.bonces.webservicesbonces.quota.domain.persistence.QuotaRepository;
import com.bonces.webservicesbonces.quota.domain.service.QuotaService;
import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;
import com.bonces.webservicesbonces.results.domain.persistence.ProfitabilityResultsRepository;
import com.bonces.webservicesbonces.results.domain.persistence.ResultsOfCurrentPriceAndProfitRepository;
import com.bonces.webservicesbonces.results.domain.persistence.ResultsOfDecisionRatioRepository;
import com.bonces.webservicesbonces.results.domain.persistence.StructuringResultsRepository;
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
    private final BoundDataRepository boundDataRepository;
    private final InitialCostDataRepository initialCostDataRepository;
    private final StructuringResultsRepository structuringResultsRepository;
    private final ResultsOfCurrentPriceAndProfitRepository resultsOfCurrentPriceAndProfitRepository;
    private final ResultsOfDecisionRatioRepository resultsOfDecisionRatioRepository;
    private final ProfitabilityResultsRepository profitabilityResultsRepository;
    private final QuotaRepository quotaRepository;
    private final Validator validator;

    private final BoundDataService boundDataService;
    private final InitialCostDataService initialCostDataService;
    private final StructuringResultsService structuringResultsService;
    private final ResultsOfCurrentPriceAndProfitService resultsOfCurrentPriceAndProfitService;
    private final ResultsOfDecisionRatioService resultsOfDecisionRatioService;
    private final ProfitabilityResultsService profitabilityResultsService;
    private final QuotaService quotaService;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository,
                               BoundDataRepository boundDataRepository, InitialCostDataRepository initialCostDataRepository,
                               StructuringResultsRepository structuringResultsRepository,
                               ResultsOfCurrentPriceAndProfitRepository resultsOfCurrentPriceAndProfitRepository,
                               ResultsOfDecisionRatioRepository resultsOfDecisionRatioRepository,
                               ProfitabilityResultsRepository profitabilityResultsRepository, QuotaRepository quotaRepository,
                               Validator validator, BoundDataService boundDataService, InitialCostDataService initialCostDataService,
                               StructuringResultsService structuringResultsService,
                               ResultsOfCurrentPriceAndProfitService resultsOfCurrentPriceAndProfitService,
                               ResultsOfDecisionRatioService resultsOfDecisionRatioService,
                               ProfitabilityResultsService profitabilityResultsService, QuotaService quotaService) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.boundDataRepository = boundDataRepository;
        this.initialCostDataRepository = initialCostDataRepository;
        this.structuringResultsRepository = structuringResultsRepository;
        this.resultsOfCurrentPriceAndProfitRepository = resultsOfCurrentPriceAndProfitRepository;
        this.resultsOfDecisionRatioRepository = resultsOfDecisionRatioRepository;
        this.profitabilityResultsRepository = profitabilityResultsRepository;
        this.quotaRepository = quotaRepository;
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

        FrenchAlgorithm frenchAlgorithm = new FrenchAlgorithm(request.getBoundData(), request.getInitialCostData(), request.getDate());

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));

        Schedule schedule = new Schedule();
        schedule = scheduleRepository.save(schedule.withUser(user).withDate(request.getDate()));

        BoundData boundData = boundDataService.createBoundData(schedule.getId(), frenchAlgorithm.boundData);
        InitialCostData initialCostData = initialCostDataService.createInitialCostData(schedule.getId(), frenchAlgorithm.initialCostData);
        StructuringResults structuringResults = structuringResultsService.createStructuringResults(schedule.getId(), frenchAlgorithm.structuringResults);
        ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit = resultsOfCurrentPriceAndProfitService.createResultsOfCurrentPriceAndProfit(schedule.getId(), frenchAlgorithm.resultsOfCurrentPriceAndProfit);
        ResultsOfDecisionRatio resultsOfDecisionRatio = resultsOfDecisionRatioService.createResultsOfDecisionRatio(schedule.getId(), frenchAlgorithm.resultsOfDecisionRatio);
        ProfitabilityResults profitabilityResults = profitabilityResultsService.createProfitabilityResults(schedule.getId(), frenchAlgorithm.profitabilityResults);
        Set<Quota> quotas = quotaService.createQuotas(schedule.getId(), frenchAlgorithm.quotas);

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
            FrenchAlgorithm frenchAlgorithm = new FrenchAlgorithm(request.getBoundData(), request.getInitialCostData(), request.getDate());

            BoundData boundData = boundDataService.updateBoundData(schedule.getId(), frenchAlgorithm.boundData);
            InitialCostData initialCostData = initialCostDataService.updateInitialCostData(schedule.getId(), frenchAlgorithm.initialCostData);
            StructuringResults structuringResults = structuringResultsService.updateStructuringResults(schedule.getId(), frenchAlgorithm.structuringResults);
            ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit = resultsOfCurrentPriceAndProfitService.updateResultsOfCurrentPriceAndProfit(schedule.getId(), frenchAlgorithm.resultsOfCurrentPriceAndProfit);
            ResultsOfDecisionRatio resultsOfDecisionRatio = resultsOfDecisionRatioService.updateResultsOfDecisionRatio(schedule.getId(), frenchAlgorithm.resultsOfDecisionRatio);
            ProfitabilityResults profitabilityResults = profitabilityResultsService.updateProfitabilityResults(schedule.getId(), frenchAlgorithm.profitabilityResults);
            Set<Quota> quotas = quotaService.updateQuotas(schedule.getId(), frenchAlgorithm.quotas);

            schedule.setDate(request.getDate());
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
