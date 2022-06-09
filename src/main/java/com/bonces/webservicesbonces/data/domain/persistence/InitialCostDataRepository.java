package com.bonces.webservicesbonces.data.domain.persistence;

import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialCostDataRepository extends JpaRepository<InitialCostData, Long>{
}
