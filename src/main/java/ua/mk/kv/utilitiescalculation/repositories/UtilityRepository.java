package ua.mk.kv.utilitiescalculation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.mk.kv.utilitiescalculation.entities.CounterData;
import ua.mk.kv.utilitiescalculation.entities.Utility;

import java.util.List;

public interface UtilityRepository extends JpaRepository<Utility, String> {

    @Query(value = """
        select * 
        from utility 
        order by use_counter DESC, id        
    """, nativeQuery = true)
    List<Utility> findAll();

}