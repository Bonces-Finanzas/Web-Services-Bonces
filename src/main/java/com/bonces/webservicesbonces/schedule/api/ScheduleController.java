package com.bonces.webservicesbonces.schedule.api;

import com.bonces.webservicesbonces.schedule.domain.service.ScheduleService;
import com.bonces.webservicesbonces.schedule.mapping.ScheduleMapper;
import com.bonces.webservicesbonces.schedule.resource.ScheduleResource;
import com.bonces.webservicesbonces.schedule.resource.create.CreateScheduleResource;
import com.bonces.webservicesbonces.schedule.resource.update.UpdateScheduleResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/schedule")
@CrossOrigin
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping("{scheduleId}")
    public ScheduleResource getScheduleById(@PathVariable Long scheduleId) {
        return scheduleMapper.toResource(scheduleService.getById(scheduleId));
    }

    @PostMapping()
    public ScheduleResource createSchedule(@RequestParam(name = "userId") Long userId,
                                           @Valid @RequestBody CreateScheduleResource request) {
        return scheduleMapper.toResource(scheduleService.create(userId, scheduleMapper.toModel(request)));
    }

    @PutMapping("{scheduleId}")
    public ScheduleResource updateSchedule(@PathVariable Long scheduleId,
                                           @Valid @RequestBody UpdateScheduleResource request) {
        return scheduleMapper.toResource(scheduleService.update(scheduleId, scheduleMapper.toModel(request)));
    }

    @DeleteMapping("{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
        return scheduleService.delete(scheduleId);
    }
}
