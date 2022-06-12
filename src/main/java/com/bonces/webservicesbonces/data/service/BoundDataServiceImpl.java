package com.bonces.webservicesbonces.data.service;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import com.bonces.webservicesbonces.data.domain.persistence.BoundDataRepository;
import com.bonces.webservicesbonces.data.domain.service.BoundDataService;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.schedule.domain.persistence.ScheduleRepository;
import com.bonces.webservicesbonces.shared.exception.ResourceNotFoundException;
import com.bonces.webservicesbonces.shared.exception.ResourceValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class BoundDataServiceImpl implements BoundDataService {
    private static final String ENTITY = "BoundData";
    private final BoundDataRepository boundDataRepository;
    private final ScheduleRepository scheduleRepository;
    private final Validator validator;

    public BoundDataServiceImpl(BoundDataRepository boundDataRepository, ScheduleRepository scheduleRepository, Validator validator) {
        this.boundDataRepository = boundDataRepository;
        this.scheduleRepository = scheduleRepository;
        this.validator = validator;
    }

    @Override
    public BoundData createBoundData(Long scheduleId, BoundData request) {
        Set<ConstraintViolation<BoundData>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ResourceNotFoundException("Schedule", scheduleId));;

        request.setSchedule(schedule);

        return boundDataRepository.save(request);
    }

    @Override
    public BoundData updateBoundData(Long scheduleId, BoundData request) {
        Set<ConstraintViolation<BoundData>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return boundDataRepository.findByScheduleId(scheduleId).map(boundData ->
                boundDataRepository.save(boundData.withNominalValue(request.getNominalValue())
                        .withCommercialValue(request.getCommercialValue())
                        .withYears(request.getYears())
                        .withCouponFrequency(request.getCouponFrequency())
                        .withDaysYear(request.getDaysYear())
                        .withTypeInterestRate(request.getTypeInterestRate())
                        .withCapitalization(request.getCapitalization())
                        .withInterestRate(request.getInterestRate())
                        .withAnnualDiscountRate(request.getAnnualDiscountRate())
                        .withIncomeTax(request.getIncomeTax())
                        .withIssue(request.getIssue())
                        .withGracePeriod(request.getGracePeriod())
                        .withTypeOfGracePeriod(request.getTypeOfGracePeriod()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }
}
