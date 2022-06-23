package com.bonces.webservicesbonces.schedule.domain.model.entity;

import com.bonces.webservicesbonces.data.domain.model.entity.BoundData;
import com.bonces.webservicesbonces.data.domain.model.entity.InitialCostData;
import com.bonces.webservicesbonces.quota.domain.model.entity.Quota;
import com.bonces.webservicesbonces.results.domain.model.entity.ProfitabilityResults;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfCurrentPriceAndProfit;
import com.bonces.webservicesbonces.results.domain.model.entity.ResultsOfDecisionRatio;
import com.bonces.webservicesbonces.results.domain.model.entity.StructuringResults;
import com.bonces.webservicesbonces.schedule.domain.model.enums.CurrencyType;
import com.bonces.webservicesbonces.schedule.domain.model.enums.MethodType;
import com.bonces.webservicesbonces.shared.domain.model.entity.AuditModel;
import com.bonces.webservicesbonces.users.domain.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "schedules")
@Inheritance(strategy = InheritanceType.JOINED)
public class Schedule extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private User user;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private MethodType methodType;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CurrencyType currencyType;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(
            targetEntity = Quota.class,
            mappedBy = "schedule",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Quota> quotas;

    @OneToOne(
            targetEntity = ResultsOfCurrentPriceAndProfit.class,
            mappedBy = "schedule",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private ResultsOfCurrentPriceAndProfit resultsOfCurrentPriceAndProfit;

    @OneToOne(
            targetEntity = ResultsOfDecisionRatio.class,
            mappedBy = "schedule",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private ResultsOfDecisionRatio resultsOfDecisionRatio;

    @OneToOne(
            targetEntity = BoundData.class,
            mappedBy = "schedule",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private BoundData boundData;

    @OneToOne(
            targetEntity = ProfitabilityResults.class,
            mappedBy = "schedule",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private ProfitabilityResults profitabilityResults;

    @OneToOne(
            targetEntity = StructuringResults.class,
            mappedBy = "schedule",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private StructuringResults structuringResults;

    @OneToOne(
            targetEntity = InitialCostData.class,
            mappedBy = "schedule",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private InitialCostData initialCostData;
}
