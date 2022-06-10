package com.bonces.webservicesbonces.data.domain.model.entity;

import com.bonces.webservicesbonces.data.domain.model.enums.Capitalization;
import com.bonces.webservicesbonces.data.domain.model.enums.CouponFrequency;
import com.bonces.webservicesbonces.data.domain.model.enums.TypeInterestRate;
import com.bonces.webservicesbonces.quota.domain.model.enums.TypeOfGracePeriod;
import com.bonces.webservicesbonces.schedule.domain.model.entity.Schedule;
import com.bonces.webservicesbonces.shared.domain.model.entity.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "boundData")
@Inheritance(strategy = InheritanceType.JOINED)
public class BoundData extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double nominalValue;

    @NotNull
    private Double commercialValue;

    @NotNull
    private int years;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private CouponFrequency couponFrequency;

    @NotNull
    private int daysYear;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TypeInterestRate typeInterestRate;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Capitalization capitalization;

    @NotNull
    private Double interestRate;

    @NotNull
    private Double annualDiscountRate;

    @NotNull
    private Double incomeTax;

    @NotNull
    private Date issue;

    @NotNull
    private Long gracePeriod;

    @NotNull
    private TypeOfGracePeriod typeOfGracePeriod;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "schedule_id",
            referencedColumnName = "id",
            nullable = false
    )

    @JsonIgnore
    private Schedule schedule;
}