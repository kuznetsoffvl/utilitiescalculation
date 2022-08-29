package ua.mk.kv.utilitiescalculation.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UtilityDto implements Serializable {
    private String id;
    private String name;
    private boolean useCounter;
}
