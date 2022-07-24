package co.unicauca.edu.schedule.dao;


import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFranjaHorariaRepository extends JpaRepository<FranjaHoraria,Integer> {

    @Query(value="SELECT *  FROM franjahoraria  WHERE us_id = :id ", nativeQuery = true)
    public List<FranjaHoraria> findByIdDocente(@Param("id") String id);
}
