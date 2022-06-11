package com.bonces.webservicesbonces.results.domain.persistence;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultsOfDecisionRatioRepository extends JpaRepository<ResultsOfDecisionRatio,Long> {
    Optional<ResultsOfDecisionRatio> findByScheduleId(Long scheduleId);
}
