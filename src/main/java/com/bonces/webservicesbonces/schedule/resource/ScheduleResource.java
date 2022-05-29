package com.bonces.webservicesbonces.schedule.resource;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;
import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;
import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;
import com.bonces.webservicesbonces.users.domain.model.entity.User;
import lombok.*;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class ScheduleResource {
    private Long id;
    private Date date;
    private User user;
    private Set<Quota> quotas;
    private ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit;
    private ResultsOfDecisionRatio resultsOfDecisionRatio;
    private BoundData boundData;
    private ProfitabilityResults profitabilityResults;
    private StructuringResults structuringResults;
    private InitialCostData initialCostData;
}
