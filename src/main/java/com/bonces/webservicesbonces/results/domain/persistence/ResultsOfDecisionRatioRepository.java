package com.bonces.webservicesbonces.results.domain.persistence;

import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultsOfDecisionRatioRepository extends JpaRepository<ResultsOfDecisionRatio,Long> {
}
