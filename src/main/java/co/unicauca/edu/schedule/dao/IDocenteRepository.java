package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDocenteRepository extends JpaRepository<Docente,String> {

    @Query(value="SELECT * from docente where doc_is_available = true", nativeQuery = true)
    public List<Docente> findAllAvailable();
}
