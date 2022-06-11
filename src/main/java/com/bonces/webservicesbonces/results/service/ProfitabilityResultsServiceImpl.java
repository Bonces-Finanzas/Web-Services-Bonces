package com.bonces.webservicesbonces.results.service;

import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;
import com.bonces.webservicesbonces.results.domain.persistence.ProfitabilityResultsRepository;
import com.bonces.webservicesbonces.results.domain.service.ProfitabilityResultsService;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ProfitabilityResultsServiceImpl implements ProfitabilityResultsService {
    private static final String ENTITY = "ProfitabilityResults";
    private final ProfitabilityResultsRepository profitabilityResultsRepository;
    private final ScheduleRepository scheduleRepository;
    private final Validator validator;

    public ProfitabilityResultsServiceImpl(ProfitabilityResultsRepository profitabilityResultsRepository, ScheduleRepository scheduleRepository, Validator validator) {
        this.profitabilityResultsRepository = profitabilityResultsRepository;
        this.scheduleRepository = scheduleRepository;
        this.validator = validator;
    }

    @Override
    public ProfitabilityResults createProfitabilityResults(Long scheduleId, ProfitabilityResults request) {
        Set<ConstraintViolation<ProfitabilityResults>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule", scheduleId));;

        request.setSchedule(schedule);

        return profitabilityResultsRepository.save(request);
    }

    @Override
    public ProfitabilityResults updateProfitabilityResults(Long scheduleId, ProfitabilityResults request) {
        Set<ConstraintViolation<ProfitabilityResults>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return profitabilityResultsRepository.findByScheduleId(scheduleId).map(profitabilityResults ->
                profitabilityResultsRepository.save(profitabilityResults.withEmitterTcea(request.getEmitterTcea())
                        .withEmitterTceaWithShield(request.getEmitterTceaWithShield())
                        .withBondHolderTrea(request.getBondHolderTrea())
                        .withEmitterTirTcea(request.getEmitterTirTcea())
                        .withEmitterTirTceaWithShield(request.getEmitterTirTceaWithShield())
                        .withBondholderTirTrea(request.getBondholderTirTrea()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }
}
