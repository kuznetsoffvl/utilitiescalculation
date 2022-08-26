package ua.mk.kv.utilitiescalculation.conterollers;

import lombok.AllArgsConstructor;
import net.bytebuddy.dynamic.DynamicType;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.mk.kv.utilitiescalculation.convertors.RateConvertor;
import ua.mk.kv.utilitiescalculation.dto.RateDto;
import ua.mk.kv.utilitiescalculation.entities.CounterData;
import ua.mk.kv.utilitiescalculation.entities.Rate;
import ua.mk.kv.utilitiescalculation.entities.Utility;
import ua.mk.kv.utilitiescalculation.repositories.CounterDataRepository;
import ua.mk.kv.utilitiescalculation.repositories.RateRepository;
import ua.mk.kv.utilitiescalculation.repositories.UtilityRepository;
import ua.mk.kv.utilitiescalculation.services.ExpressionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DBController {

    private UtilityRepository utilityRepository;
    private RateRepository rateRepository;
    private CounterDataRepository counterDataRepository;
    private RateConvertor rateConvertor;

    @GetMapping("/utilities")
    public String showAllUtilities(Model model){

        List<Utility> utilities = utilityRepository.findAll();

        model.addAttribute("uts", utilities);
        return "utilities";
    }

    @PostMapping("/add_utility")
    public String addUtility(@RequestParam String utility) {
        Utility u = new Utility();
        u.setName(utility);
        u.setId(utility);
        utilityRepository.save(u);
        return "redirect:/utilities";
    }

    @GetMapping("/delete_utility/{id}")
    public String deleteUtility(@PathVariable("id") String id) {
        utilityRepository.deleteById(id);
        return "redirect:/utilities";
    }

    @GetMapping("/edit_utility/{id}")
    public String showUpdateUtilityPage(@PathVariable("id") String id, Model model){
        Optional<Utility> utility = utilityRepository.findById(id);
        if(utility.isEmpty()){
            model.addAttribute("message", "Вида услуг с ID " + id + " не найдено");
            return "error";
        } else {
            model.addAttribute("ut", utility.get());
            return "edit_utility";
        }
    }

    @PostMapping("/update_utility/{id}")
    public String updateUtility(@PathVariable("id") String id, Utility utility){
        Utility ut = utilityRepository.findById(id).get();
        ut.setId(utility.getId());
        ut.setName(utility.getName());
        ut.setUseCounter(utility.getUseCounter());
        utilityRepository.save(ut);
        return "redirect:/utilities";
    }

    @GetMapping("/rates")
    public String showAllRates(Model model){

        List<RateDto> rates = rateRepository
                .findAll()
                .stream()
                .map(rateConvertor::rateToRateDto)
                .sorted((o1,o2) -> o1.getPeriod().compareTo(o2.getPeriod()))
                .toList();

        model.addAttribute("rates", rates);
        return "rates";
    }

    @PostMapping("/add_rate")
    public String addRate(@RequestParam String utility,
                          @RequestParam String period,
                          @RequestParam Double tariff1,
                          @RequestParam Double limit1,
                          @RequestParam Double tariff2,
                          @RequestParam Double subscriptionFee,
                          @RequestParam String formula,
                          Model model
    ) {
//        Rate rate = new Rate();
        Optional<Utility> ut = utilityRepository.findById(utility);
        if (ut.isEmpty()) {
            model.addAttribute("message", "Вида услуг с ID " + utility + " не найдено");
            return "error";
        }
//
//        LocalDate localDate = LocalDate.parse(period, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        rate.setUtility(ut.get());
//        rate.setPeriod(localDate);
//        rate.setTariff1(tariff1);
//        rate.setLimit1(limit1);
//        rate.setTariff2(tariff2);
//        rate.setSubscriptionFee(subscriptionFee);
//        rate.setFormula(formula);
//        rateRepository.save(rate);

        Rate rate = rateConvertor.rateDtoToRate(RateDto.builder()
            .period(period)
            .tariff1(tariff1)
            .tariff2(tariff2)
            .limit1(limit1)
            .subscriptionFee(subscriptionFee)
            .formula(formula)
            //.utility(ut.get().getId())
            .build()
        );

        rate.setUtility(ut.get());

        return "redirect:/rates";
    }

    @GetMapping("/delete_rate/{id}")
    public String deleteRate(@PathVariable("id") int id) {
        rateRepository.deleteById(id);
        return "redirect:/rates";
    }

    @GetMapping("/edit_rate/{id}")
    public String showUpdateRatePage(@PathVariable("id") int id, Model model){
        Optional<Rate> optionalRate = rateRepository.findById(id);
        if(optionalRate.isEmpty()){
            model.addAttribute("message", "Тарифа с ID " + id + " не найдено");
            return "error";
        }
        model.addAttribute("obj", rateConvertor.rateToRateDto(optionalRate.get()));
        //model.addAttribute("period", optionalRate.get().getPeriod().format(DateTimeFormatter.ISO_DATE));
        return "edit_rate";
    }

    //@PostMapping("/update_rate/{id}/{period}")
    @PostMapping("/update_rate/{id}")
    public String updateRate(
            //@RequestParam("period") String period,
            //@PathVariable("period") String period,
            @PathVariable("id") int id,
            RateDto rateDto,
            Model model
    ){

        Optional<Rate> optionalRate = rateRepository.findById(id);

        if(optionalRate.isEmpty()){
            model.addAttribute("message", "Тарифа с ID " + id + " не найдено");
            return "error";
        }

        Rate resultRate = optionalRate.get();

        Rate rate = rateConvertor.rateDtoToRate(rateDto);

        Optional<Utility> utilityOptional = utilityRepository.findById(rateDto.getUtility());
        if (utilityOptional.isEmpty()) {
            model.addAttribute("message", "Вида услуг с ID " + rateDto.getUtility() + " не найдено");
            return "error";
        }

        rate.setUtility(utilityOptional.get());

        //LocalDate date = LocalDate.now(); //LocalDate.parse(period, DateTimeFormatter.ISO_DATE);
        //resultRate.setPeriod(date);

        resultRate.setPeriod(rate.getPeriod());
        resultRate.setTariff1(rate.getTariff1());
        resultRate.setLimit1(rate.getLimit1());
        resultRate.setTariff2(rate.getTariff2());
        resultRate.setSubscriptionFee(rate.getSubscriptionFee());
        resultRate.setFormula(rate.getFormula());
        resultRate.setUtility(rate.getUtility());

//        Optional<Utility> ut = utilityRepository.findById(rateDto.getUtility());
//
//        if (ut.isPresent()) {
//            resultRate.setUtility(ut.get());
//        }

        rateRepository.save(resultRate);

        return "redirect:/rates";
    }

    @GetMapping("/counters_data")
    public String showAllCountersData(Model model){
        List<CounterData> counterData = counterDataRepository.findAll();
        model.addAttribute("counterData", counterData);
        LocalDate date = LocalDate.now();
        date = LocalDate.of(2021, 12, 1);
        if (counterData.size() > 0) {
            //date = counterData.get(counterData.size() - 1).getPeriod().plusMonths(1);
            date = LocalDate.of(2021, 12, 1);
        }
        model.addAttribute("period",
                date.format(DateTimeFormatter.ISO_DATE));
        return "counters_data";
    }

    @GetMapping("/delete_counterData/{id}")
    public String deletecounterData(@PathVariable("id") int id) {
        counterDataRepository.deleteById(id);
        return "redirect:/counters_data";
    }

    @PostMapping("/add_counterData")
    public String addCounterData(
            @RequestParam String utility,
            @RequestParam String period,
            @RequestParam Double currentMeters,
            Model model
    ) {
        CounterData counterData = new CounterData();

        Optional<Utility> ut = utilityRepository.findById(utility);
        if (ut.isEmpty()) {
            model.addAttribute("message", "Вида услуг с ID " + utility + " не найдено");
            return "error";
        }

        LocalDate localDate = LocalDate.parse(period, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Optional<CounterData> previousCounterData = counterDataRepository.findPreviousCounterData(
                localDate, ut.get().getId());

        Double consumption = currentMeters
                - (previousCounterData.isEmpty() ? 0.0 : previousCounterData.get().getCurrentMeters());

        Optional<Rate> actualRateFromDB = rateRepository.findActualRate(
                localDate, ut.get().getId());

        Rate actualRate = actualRateFromDB.isEmpty() ? new Rate() : actualRateFromDB.get();

        ExpressionService exps = new ExpressionService(actualRate, consumption);
        Double accruedAmount = exps.calculate();

        counterData.setUtility(ut.get());
        counterData.setPeriod(localDate);
        counterData.setCurrentMeters(currentMeters);
        counterData.setConsumption(consumption);
        counterData.setRate(actualRate);
        counterData.setAccruedAmount(accruedAmount);

        counterDataRepository.save(counterData);
        return "redirect:/counters_data";
    }

}
