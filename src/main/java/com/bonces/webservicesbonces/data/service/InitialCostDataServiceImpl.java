package com.bonces.webservicesbonces.data.service;

import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;
import com.bonces.webservicesbonces.data.domain.persistence.InitialCostDataRepository;
import com.bonces.webservicesbonces.data.domain.service.InitialCostDataService;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class InitialCostDataServiceImpl implements InitialCostDataService {
    private static final String ENTITY = "InitialCostData";
    private final InitialCostDataRepository initialCostDataRepository;
    private final ScheduleRepository scheduleRepository;
    private final Validator validator;

    public InitialCostDataServiceImpl(InitialCostDataRepository initialCostDataRepository, ScheduleRepository scheduleRepository, Validator validator) {
        this.initialCostDataRepository = initialCostDataRepository;
        this.scheduleRepository = scheduleRepository;
        this.validator = validator;
    }

    @Override
    public InitialCostData createInitialCostData(Long scheduleId, InitialCostData request) {
        Set<ConstraintViolation<InitialCostData>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule", scheduleId));;

        request.setSchedule(schedule);

        return initialCostDataRepository.save(request);
    }

    @Override
    public InitialCostData updateInitialCostData(Long scheduleId, InitialCostData request) {
        Set<ConstraintViolation<InitialCostData>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return initialCostDataRepository.findByScheduleId(scheduleId).map(initialCostData ->
                initialCostDataRepository.save(initialCostData.withPremium(request.getPremium())
                        .withStructuring(request.getStructuring())
                        .withCollocation(request.getCollocation())
                        .withCavali(request.getCavali()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }
}
