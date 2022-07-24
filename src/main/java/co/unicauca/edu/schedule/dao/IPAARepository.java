package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPAARepository extends JpaRepository<PeriodoAcademicoAmbiente,Integer> {

    @Query
    public List<PeriodoAcademicoAmbiente> findByHor(Integer id);
}
