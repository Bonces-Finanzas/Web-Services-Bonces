package com.bonces.webservicesbonces.quota.domain.persistence;

import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {
}

