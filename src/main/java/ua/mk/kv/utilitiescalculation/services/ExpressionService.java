package ua.mk.kv.utilitiescalculation.services;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import ua.mk.kv.utilitiescalculation.entities.Rate;

public class ExpressionService {

    private Rate rate;
    private Double consumption;

    public ExpressionService(Rate rate, Double consumption) {
        this.rate = rate;
        this.consumption = consumption;
    }

    public double calculate() {

        int useTariff1 = 1;
        if (rate.getLimit1() != null && consumption > rate.getLimit1()) {
            useTariff1 = 0;
        }

        String strExpression = rate.getFormula(); // ? "0" : rate.getFormula();
        if (strExpression.isEmpty()) {
            return 0.0;
        }


        Expression expression = new ExpressionBuilder(strExpression)
                .variables("tariff1", "tariff2", "subscriptionFee", "consumption", "useTariff1")
                .build()
                .setVariable("tariff1", rate.getTariff1())
                .setVariable("tariff2", rate.getTariff2())
                .setVariable("subscriptionFee", rate.getSubscriptionFee())
                .setVariable("consumption", consumption)
                .setVariable("useTariff1", useTariff1);

        double result = Math.round(expression.evaluate() * 100d) / 100d ; // приводим к сумме с копейками XXXX.XX
        return result;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

}
