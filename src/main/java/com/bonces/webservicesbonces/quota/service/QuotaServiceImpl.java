package com.bonces.webservicesbonces.quota.service;

import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;
import com.bonces.webservicesbonces.quota.domain.persistence.QuotaRepository;
import com.bonces.webservicesbonces.quota.domain.service.QuotaService;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Service
public class QuotaServiceImpl implements QuotaService {
    private static final String ENTITY = "Quota";
    private final QuotaRepository quotaRepository;
    private final ScheduleRepository scheduleRepository;
    private final Validator validator;

    public QuotaServiceImpl(QuotaRepository quotaRepository, ScheduleRepository scheduleRepository, Validator validator) {
        this.quotaRepository = quotaRepository;
        this.scheduleRepository = scheduleRepository;
        this.validator = validator;
    }

    @Override
    public Set<Quota> createQuotas(Long scheduleId, Set<Quota> quotas) {
        for (Quota quota : quotas) {
            Set<ConstraintViolation<Quota>> violations = validator.validate(quota);

            if (!violations.isEmpty())
                throw new ResourceValidationException(ENTITY, violations);
        }

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule", scheduleId));;

        quotas.forEach(quota -> quota.setSchedule(schedule));

        return new HashSet<>(quotaRepository.saveAll(quotas));
    }

    @Override
    public Set<Quota> updateQuotas(Long scheduleId, Set<Quota> quotas) {
        for (Quota quota : quotas) {
            Set<ConstraintViolation<Quota>> violations = validator.validate(quota);

            if (!violations.isEmpty())
                throw new ResourceValidationException(ENTITY, violations);
        }

        quotaRepository.findByScheduleId(scheduleId).map(quota -> {
            quotaRepository.deleteAll(quota);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));

        return createQuotas(scheduleId, quotas);
    }
}
