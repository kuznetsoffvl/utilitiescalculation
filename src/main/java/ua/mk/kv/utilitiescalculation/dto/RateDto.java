package ua.mk.kv.utilitiescalculation.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RateDto implements Serializable {
    private Integer id;
    private String period;
    private Double tariff1;
    private Double limit1;
    private Double tariff2;
    private Double subscriptionFee;
    private String formula;
    private String utility;
}
