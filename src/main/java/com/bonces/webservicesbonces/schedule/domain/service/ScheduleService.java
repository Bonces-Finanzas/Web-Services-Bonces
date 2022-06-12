package com.bonces.webservicesbonces.schedule.domain.service;

import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    Schedule getById(Long scheduleId);
    Schedule create(Long userId, Schedule request);
    Schedule update(Long scheduleId, Schedule request);
    ResponseEntity<?> delete(Long scheduleId);
}
