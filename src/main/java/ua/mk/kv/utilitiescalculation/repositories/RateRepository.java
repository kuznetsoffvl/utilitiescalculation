package ua.mk.kv.utilitiescalculation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.mk.kv.utilitiescalculation.entities.Rate;

import java.time.LocalDate;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Integer> {

    @Query(value = """
           select
               *
           from rate
           where period <= :date
               and utility_id = :utility_id
           order by period DESC
           limit 1""",
            nativeQuery = true)
    Optional<Rate> findActualRate(@Param("date") LocalDate date, @Param("utility_id") String utility_id);
}