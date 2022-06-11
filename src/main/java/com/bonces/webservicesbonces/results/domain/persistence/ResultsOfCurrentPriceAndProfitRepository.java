package com.bonces.webservicesbonces.results.domain.persistence;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultsOfCurrentPriceAndProfitRepository extends JpaRepository<ResultsOfCurrentPriceAndProfit, Long> {
    Optional<ResultsOfCurrentPriceAndProfit> findByScheduleId(Long scheduleId);
}
