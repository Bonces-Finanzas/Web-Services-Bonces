package com.bonces.webservicesbonces.data.resource;

import com.bonces.webservicesbonces.shared.resource.AuditModelResource;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
public class InitialCostDataResource extends AuditModelResource {
    private Long id;
    private Double premium;
    private Double structuring;
    private Double collocation;
    private String floatation;
    private Double cavali;
}
