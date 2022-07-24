package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.dto.DocenteDTO;

public interface IDocenteService {
    public Docente save(Docente doc);

    public Docente findById(String id);
}
