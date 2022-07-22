package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAmbienteRepository extends JpaRepository<Ambiente,String> {


}
