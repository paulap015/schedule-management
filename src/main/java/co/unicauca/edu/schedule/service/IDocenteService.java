package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.dto.DocenteDTO;

import java.util.List;

public interface IDocenteService {
    public Docente save(Docente doc);

    public Docente findById(String id);

    public List<Docente> findAll();
}
