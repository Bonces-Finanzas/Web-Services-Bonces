package com.bonces.webservicesbonces.quota.domain.service;

import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;

import java.util.Set;

public interface QuotaService {
    Set<Quota> createQuotas(Long scheduleId, Set<Quota> quotas);
    Set<Quota> updateQuotas(Long scheduleId, Set<Quota> quotas);
}
