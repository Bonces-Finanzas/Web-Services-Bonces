package com.bonces.webservicesbonces.data.domain.persistence;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoundDataRepository extends JpaRepository<BoundData, Long> {
    Optional<BoundData> findByScheduleId(Long scheduleId);
}