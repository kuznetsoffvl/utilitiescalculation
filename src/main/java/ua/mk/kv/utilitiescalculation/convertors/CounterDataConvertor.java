package ua.mk.kv.utilitiescalculation.convertors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import ua.mk.kv.utilitiescalculation.dto.CounterDataDto;
import ua.mk.kv.utilitiescalculation.entities.CounterData;
import ua.mk.kv.utilitiescalculation.entities.Rate;
import ua.mk.kv.utilitiescalculation.entities.Utility;
import ua.mk.kv.utilitiescalculation.repositories.RateRepository;
import ua.mk.kv.utilitiescalculation.repositories.UtilityRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
@Component
@AllArgsConstructor
public class CounterDataConvertor {

    private UtilityRepository utilityRepository;

    private RateRepository rateRepository;

    public CounterDataDto counterDataToCounterDataDto(CounterData counterData){
        String stringPeriod = counterData.getPeriod().format(DateTimeFormatter.ISO_DATE);
        return CounterDataDto.builder()
                .id(            counterData.getId())
                .period(        stringPeriod)
                .consumption(   counterData.getConsumption())
                .accruedAmount( counterData.getAccruedAmount())
                .currentMeters( counterData.getCurrentMeters())
                .rate_id(       counterData.getRate().getId())
                .utility_id(    counterData.getUtility().getId())
                .build();
    }

    public CounterData counterDataDtoToCounterData(CounterDataDto counterDataDto) {

        Optional<Utility> optionalUtility = utilityRepository.findById(counterDataDto.getUtility_id());
        Optional<Rate> optionalRate = rateRepository.findById(counterDataDto.getRate_id());

        CounterData counterData = new CounterData();
        counterData.setId(counterDataDto.getId());
        counterData.setPeriod(LocalDate.parse(counterDataDto.getPeriod()));
        counterData.setConsumption(counterDataDto.getConsumption());
        counterData.setAccruedAmount(counterDataDto.getAccruedAmount());
        counterData.setCurrentMeters(counterDataDto.getCurrentMeters());

        optionalUtility.ifPresent(counterData::setUtility);
        optionalRate.ifPresent(counterData::setRate);

        return counterData;
    }
}
