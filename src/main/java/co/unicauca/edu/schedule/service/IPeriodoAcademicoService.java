package co.unicauca.edu.schedule.service;
import java.util.List;
import java.util.Optional;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademico;

public interface IPeriodoAcademicoService {
    public List<PeriodoAcademico> findAll();
    public Optional<PeriodoAcademico> findById(int id);
    public PeriodoAcademico save(PeriodoAcademico periodo);
    public void deleteById(int id);
    public PeriodoAcademico update(PeriodoAcademico save);

    public boolean periodoAcademicoTieneReferencias(Integer  pa);
}
