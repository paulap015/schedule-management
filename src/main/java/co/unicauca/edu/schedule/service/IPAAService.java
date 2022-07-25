package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.domain.model.Ambiente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademico;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPAAService {

    public PeriodoAcademicoAmbiente save(FranjaDTO franjaDTO, FranjaHoraria fran);

    public List<PeriodoAcademicoAmbiente> findAll();

    public List<PeriodoAcademicoAmbiente> findAllByPa(Integer pa);
    public PeriodoAcademicoAmbiente update(FranjaDTO franjaDTO,FranjaHoraria fran);
    public PeriodoAcademicoAmbiente findByHor(@Param("id")Integer id);
}
