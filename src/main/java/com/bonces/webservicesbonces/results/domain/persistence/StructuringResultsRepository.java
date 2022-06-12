package com.bonces.webservicesbonces.results.domain.persistence;

import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StructuringResultsRepository extends JpaRepository<StructuringResults,Long> {
    Optional<StructuringResults> findByScheduleId(Long scheduleId);
}
