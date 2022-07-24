package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Cordinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICordinadorRepository  extends JpaRepository<Cordinador,Integer> {
}
