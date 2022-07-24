package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.domain.model.Ambiente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademico;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;

public interface IPAAService {

    public PeriodoAcademicoAmbiente save(FranjaDTO franjaDTO, FranjaHoraria fran);
}
