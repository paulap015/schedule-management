package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAreaRepository extends JpaRepository<Area,Integer> {
}
