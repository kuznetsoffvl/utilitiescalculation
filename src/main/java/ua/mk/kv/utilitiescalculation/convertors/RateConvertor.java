package ua.mk.kv.utilitiescalculation.convertors;

import org.springframework.stereotype.Component;
import ua.mk.kv.utilitiescalculation.dto.RateDto;
import ua.mk.kv.utilitiescalculation.entities.Rate;
import ua.mk.kv.utilitiescalculation.entities.Utility;
import ua.mk.kv.utilitiescalculation.repositories.UtilityRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class RateConvertor {

    private UtilityRepository utilityRepository;
    public RateDto rateToRateDto(Rate rate) {

        String stringPeriod = rate.getPeriod().format(DateTimeFormatter.ISO_DATE);
        return RateDto.builder()
                .id(                rate.getId())
                .tariff1(           rate.getTariff1())
                .tariff2(           rate.getTariff2())
                .limit1(            rate.getLimit1())
                .subscriptionFee(   rate.getSubscriptionFee())
                .formula(           rate.getFormula())
                .period(            stringPeriod)
                .utility(           rate.getUtility().getId())
                .build();
    }

    public Rate rateDtoToRate(RateDto rateDto) {

        Rate rate = new Rate();
        rate.setId(             rateDto.getId());
        rate.setTariff1(        rateDto.getTariff1());
        rate.setTariff2(        rateDto.getTariff2());
        rate.setLimit1(         rateDto.getLimit1());
        rate.setSubscriptionFee(rateDto.getSubscriptionFee());
        rate.setFormula(        rateDto.getFormula());
        rate.setPeriod(         LocalDate.parse( rateDto.getPeriod()));

//        Optional<Utility> ut = utilityRepository.findById(rateDto.getUtility());
//        if (ut.isPresent()) {
//            rate.setUtility(ut.get());
//        }
        return rate;
    }

}
