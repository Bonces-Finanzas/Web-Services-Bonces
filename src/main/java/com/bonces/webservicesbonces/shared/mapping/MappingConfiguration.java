package com.bonces.webservicesbonces.shared.mapping;

import com.bonces.webservicesbonces.schedule.mapping.ScheduleMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("boncesModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public ScheduleMapper scheduleMapper() { return new ScheduleMapper(); }
}
