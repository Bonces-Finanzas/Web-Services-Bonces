package com.bonces.webservicesbonces.results.service;

import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;
import com.bonces.webservicesbonces.results.domain.persistence.StructuringResultsRepository;
import com.bonces.webservicesbonces.results.domain.service.StructuringResultsService;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class StructuringResultsServiceImpl implements StructuringResultsService {
    private static final String ENTITY = "StructuringResults";
    private final StructuringResultsRepository structuringResultsRepository;
    private final ScheduleRepository scheduleRepository;
    private final Validator validator;

    public StructuringResultsServiceImpl(StructuringResultsRepository structuringResultsRepository, ScheduleRepository scheduleRepository, Validator validator) {
        this.structuringResultsRepository = structuringResultsRepository;
        this.scheduleRepository = scheduleRepository;
        this.validator = validator;
    }

    @Override
    public StructuringResults createStructuringResults(Long scheduleId, StructuringResults request) {
        Set<ConstraintViolation<StructuringResults>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule", scheduleId));;

        request.setSchedule(schedule);

        return structuringResultsRepository.save(request);
    }

    @Override
    public StructuringResults updateStructuringResults(Long scheduleId, StructuringResults request) {
        Set<ConstraintViolation<StructuringResults>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return structuringResultsRepository.findByScheduleId(scheduleId).map(structuringResults ->
                structuringResultsRepository.save(structuringResults.withCouponFrequencyDays(request.getCouponFrequencyDays())
                        .withCapitalizationDays(request.getCapitalizationDays())
                        .withPeriodsPerYear(request.getPeriodsPerYear())
                        .withTotalNumberOfPeriods(request.getTotalNumberOfPeriods())
                        .withEffectiveAnnualRate(request.getEffectiveAnnualRate())
                        .withEffectiveRate(request.getEffectiveRate())
                        .withCOK(request.getCOK())
                        .withInitialCostsEmitter(request.getInitialCostsEmitter())
                        .withInitialCostsBondholder(request.getInitialCostsBondholder()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }
}
