package com.bonces.webservicesbonces.results.domain.model.entity;

import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.shared.domain.model.entity.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "resultsOfCurrentPriceAndProfits")
@Inheritance(strategy = InheritanceType.JOINED)
public class ResultsOfCurrentPriceAndProfit extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double currentPrice;

    @NotNull
    private Double lostProfit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schedule_id",
            referencedColumnName = "id",
            nullable = false
    )
    @JsonIgnore
    private Schedule schedule;

}
