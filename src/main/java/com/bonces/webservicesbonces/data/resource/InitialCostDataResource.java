package com.bonces.webservicesbonces.data.resource;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class InitialCostDataResource {
    private Long id;
    private Double premium;
    private Double structuring;
    private Double collocation;
    private String floatation;
    private Double cavali;
}
