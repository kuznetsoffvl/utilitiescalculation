package ua.mk.kv.utilitiescalculation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.mk.kv.utilitiescalculation.entities.CounterData;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CounterDataRepository extends JpaRepository<CounterData, Integer> {

    @Query(value = """
            select
                *
            from counter_data
            where period <= :date
              and utility_id = :utility_id
            order by period DESC
            limit 1
    """,
            nativeQuery = true)
    Optional<CounterData> findPreviousCounterData(@Param("date") LocalDate date, @Param("utility_id") String utility_id);


    //@Query(value = "select * from counter_data order by period, utility_id ", nativeQuery = true)
    @Query(value = "select * from counter_data", nativeQuery = true)
    List<CounterData> findAll();
}