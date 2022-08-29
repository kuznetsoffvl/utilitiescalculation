package ua.mk.kv.utilitiescalculation.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class CounterDataDto implements Serializable {
    private Integer id;
    private String period;
    private Double currentMeters;
    private Double accruedAmount;
    private Double consumption;
    private String utility_id;
    private Integer rate_id;
}
