package co.unicauca.edu.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.unicauca.edu.schedule.model.PeriodoAcademico;

public interface IPeriodoAcademicoRepository extends JpaRepository<PeriodoAcademico, Integer> {

}
    
