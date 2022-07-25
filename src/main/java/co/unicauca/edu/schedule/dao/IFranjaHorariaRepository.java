package co.unicauca.edu.schedule.dao;


import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFranjaHorariaRepository extends JpaRepository<FranjaHoraria,Integer> {

    @Query(value="SELECT *  FROM franjahoraria  WHERE us_id = :id ", nativeQuery = true)
    public List<FranjaHoraria> findByIdDocente(@Param("id") String id);

    @Query(value = "SELECT COUNT(*) FROM franjahoraria where hor_dia = :dia and us_id = :docente", nativeQuery = true)
    public int horasDiaDocente(@Param("dia") String dia, @Param("docente") String doc);

    @Query(value="SELECT * FROM franjahoraria where us_id = :doc and hor_dia = :dia", nativeQuery = true)
    public List<FranjaHoraria> franjasDocenteDiaOcupado(@Param("dia") String dia,@Param("doc") String doc);

    @Query(value="SELECT * FROM franjahoraria WHERE hor_hora_inicio =:horaIni and hor_dia=:dia ", nativeQuery = true)
    public List<FranjaHoraria> franjasHoraDiaOcupado(@Param("dia") String dia,@Param("horaIni") String horaIni);
}
