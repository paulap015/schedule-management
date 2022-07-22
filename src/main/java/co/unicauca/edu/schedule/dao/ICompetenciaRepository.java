package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompetenciaRepository extends JpaRepository<Competencia,Integer> {
}
