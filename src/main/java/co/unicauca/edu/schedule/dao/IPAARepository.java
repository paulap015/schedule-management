package co.unicauca.edu.schedule.dao;

import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPAARepository extends JpaRepository<PeriodoAcademicoAmbiente,Integer> {

    @Query(value="SELECT * FROM periodoacademicoambiente where hor_id= :id",nativeQuery = true)
    public PeriodoAcademicoAmbiente findByHor(@Param("id")Integer id);

    @Query(value="SELECT * FROM periodoacademicoambiente where pa_id= :idPa", nativeQuery = true)
    public List<PeriodoAcademicoAmbiente> findByPa(@Param("idPa") Integer idPa);
}
