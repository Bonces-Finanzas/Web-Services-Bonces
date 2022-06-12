package com.bonces.webservicesbonces.shared.mapping;

import com.bonces.webservicesbonces.schedule.mapping.ScheduleMapper;
import com.bonces.webservicesbonces.users.mapping.UserMapper;
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

    @Bean
    public UserMapper userMapper() { return new UserMapper(); }
}
