package com.bonces.webservicesbonces.quota.domain.persistence;

import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {
    Optional<List<Quota>> findByScheduleId(Long scheduleId);
}

