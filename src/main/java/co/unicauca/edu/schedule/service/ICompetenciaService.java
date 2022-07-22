package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.domain.model.Competencia;

import java.util.List;
import java.util.Optional;

public interface ICompetenciaService {
    public List<Competencia> findAll();
    public Optional<Competencia> findById(int id);

    public Competencia save(Competencia competencia);
    public void deleteById(int id);

    public Competencia update(Competencia competencia);
}
