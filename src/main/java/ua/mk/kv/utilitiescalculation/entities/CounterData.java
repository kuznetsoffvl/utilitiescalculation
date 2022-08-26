package ua.mk.kv.utilitiescalculation.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "counter_data")
public class CounterData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "period")
    private LocalDate period;

    @Column(name = "current_meters")
    private Double currentMeters;

    @Column(name = "accrued_amount")
    private Double accruedAmount;

    @ManyToOne
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @ManyToOne
    @JoinColumn(name = "rate_id")
    private Rate rate;

    @Column(name = "consumption")
    private Double consumption;

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Utility getUtility() {
        return utility;
    }

    public void setUtility(Utility utility) {
        this.utility = utility;
    }

    public Double getAccruedAmount() {
        return accruedAmount;
    }

    public void setAccruedAmount(Double accruedAmount) {
        this.accruedAmount = accruedAmount;
    }

    public Double getCurrentMeters() {
        return currentMeters;
    }

    public void setCurrentMeters(Double currentMeters) {
        this.currentMeters = currentMeters;
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

}