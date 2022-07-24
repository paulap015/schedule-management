package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProgramaRepository extends JpaRepository<Programa,Integer>{
}
