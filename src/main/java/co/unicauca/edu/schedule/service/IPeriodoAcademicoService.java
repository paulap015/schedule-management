package co.unicauca.edu.schedule.service;
import java.util.Optional;
import co.unicauca.edu.schedule.model.PeriodoAcademico;

public interface IPeriodoAcademicoService {
    public Iterable<PeriodoAcademico> findAll();
    public Optional<PeriodoAcademico> findById(int id);
    public PeriodoAcademico save(PeriodoAcademico periodo);
    public void deleteById(int id);
    public PeriodoAcademico update(PeriodoAcademico save);
}
