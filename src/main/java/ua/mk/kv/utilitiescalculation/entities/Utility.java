package ua.mk.kv.utilitiescalculation.entities;

import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "utility")
public class Utility {
    @Id
    @Column(name = "id", length = 25, nullable = false)
    private String id;

    @Column(name = "name", length = 25)
    private String name;

    @Column(name = "use_counter")
    private boolean useCounter;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "utility_id")
    private Collection<Rate> rates = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "utility_id")
    private Collection<CounterData> counterDatas = new ArrayList<>();

    public Collection<CounterData> getCounterDatas() {
        return counterDatas;
    }

    public void setCounterDatas(Collection<CounterData> counterDatas) {
        this.counterDatas = counterDatas;
    }

    public Collection<Rate> getRates() {
        return rates;
    }

    public void setRates(Collection<Rate> rates) {
        this.rates = rates;
    }


    public boolean getUseCounter() {
        return useCounter;
    }

    public void setUseCounter(boolean useCounter) {
        this.useCounter = useCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Utility{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", useCounter=" + useCounter +
                ", rates=" + rates +
                ", counterDatas=" + counterDatas +
                '}';
    }
}