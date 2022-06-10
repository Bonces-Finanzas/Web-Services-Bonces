package com.bonces.webservicesbonces.data.domain.persistence;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoundDataRepository extends JpaRepository<BoundData, Long> {
}