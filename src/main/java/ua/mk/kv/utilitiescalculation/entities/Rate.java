package ua.mk.kv.utilitiescalculation.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "rate")
public class Rate implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "period", nullable = false)
    private LocalDate period;

    @Column(name = "tariff1")
    private Double tariff1;

    @Column(name = "limit1")
    private Double limit1;

    @Column(name = "tariff2")
    private Double tariff2;

    @Column(name = "subscription_fee")
    private Double subscriptionFee;

    @Column(name = "formula", length = 100)
    private String formula;

    @ManyToOne
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "rate_id")
    private Collection<CounterData> counterDatas = new ArrayList<>();


    public Collection<CounterData> getCounterDatas() {
        return counterDatas;
    }

    public void setCounterDatas(Collection<CounterData> counterDatas) {
        this.counterDatas = counterDatas;
    }

    public Utility getUtility() {
        return utility;
    }

    public void setUtility(Utility utility) {
        this.utility = utility;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Double getTariff2() {
        return tariff2;
    }

    public void setTariff2(Double tariff2) {
        this.tariff2 = tariff2;
    }


    public Double getLimit1() {
        return limit1;
    }

    public void setLimit1(Double limit1) {
        this.limit1 = limit1;
    }

    public Double getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(Double subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    public Double getTariff1() {
        return tariff1;
    }

    public void setTariff1(Double tariff) {
        this.tariff1 = tariff;
    }

    public LocalDate getPeriod() {
        return period;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        if (this.getClass() != o.getClass()) return getClass().getName().compareTo(o.getClass().getName());
        Rate rate = (Rate) o;
        int v = utility.getId().compareTo(rate.utility.getId());
        if (v != 0) return v;
        v = period.compareTo(rate.period);
        return v;
    }

}