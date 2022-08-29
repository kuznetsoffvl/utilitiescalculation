package ua.mk.kv.utilitiescalculation.convertors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.mk.kv.utilitiescalculation.dto.UtilityDto;
import ua.mk.kv.utilitiescalculation.entities.Utility;
import ua.mk.kv.utilitiescalculation.repositories.UtilityRepository;


@Component
@AllArgsConstructor
public class UtilityConvertor {
    private UtilityRepository utilityRepository;
    public UtilityDto utilityToUtilityDto(Utility utility) {

        return UtilityDto.builder()
                .id(utility.getId())
                .name(utility.getName())
                .useCounter(utility.getUseCounter())
                .build();
    }

    public Utility utilityDtoToUtility(UtilityDto utilityDto) {

        Utility utility = new Utility();
        utility.setId(utilityDto.getId());
        utility.setName(utilityDto.getName());
        utility.setUseCounter(utilityDto.isUseCounter());

        return utility;
    }
}
