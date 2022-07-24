package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocenteRepository extends JpaRepository<Docente,String> {

}
