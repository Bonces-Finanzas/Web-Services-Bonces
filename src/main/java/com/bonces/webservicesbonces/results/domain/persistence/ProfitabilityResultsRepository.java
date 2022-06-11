package com.bonces.webservicesbonces.results.domain.persistence;
import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfitabilityResultsRepository extends JpaRepository<ProfitabilityResults,Long> {
    Optional<ProfitabilityResults> findByScheduleId(Long scheduleId);
}
