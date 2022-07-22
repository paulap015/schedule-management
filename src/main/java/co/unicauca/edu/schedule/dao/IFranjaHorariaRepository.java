package co.unicauca.edu.schedule.dao;


import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFranjaHorariaRepository extends JpaRepository<FranjaHoraria,Integer> {
}
