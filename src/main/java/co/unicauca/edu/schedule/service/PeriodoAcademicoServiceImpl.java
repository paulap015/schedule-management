package co.unicauca.edu.schedule.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.unicauca.edu.schedule.dao.IPeriodoAcademicoRepository;
import co.unicauca.edu.schedule.model.PeriodoAcademico;

@Service
public class PeriodoAcademicoServiceImpl implements IPeriodoAcademicoService {

    @Autowired
    private IPeriodoAcademicoRepository periodoAcademicoRepository;

    @Override
    public Iterable<PeriodoAcademico> findAll() {
        return this.periodoAcademicoRepository.findAll();
    }


    @Override
    public Optional<PeriodoAcademico> findById(int id) {
        System.out.println("Buscar en Service: " + id);
        return this.periodoAcademicoRepository.findById(id);
    }

    @Override
    public PeriodoAcademico save(PeriodoAcademico periodo) {
        return this.periodoAcademicoRepository.save(periodo);   
    }

    @Override
    public void deleteById(int id) {
        this.periodoAcademicoRepository.deleteById(id);
    }

    @Override
    public PeriodoAcademico update(PeriodoAcademico save) {
        PeriodoAcademico db = this.findById(save.getId()).get();
        if(db != null){
            db.setNombre(save.getNombre());
            db.setFechaFin(save.getFechaFin());
            db.setFechaInicio(save.getFechaInicio());
            return this.periodoAcademicoRepository.save(db);
        }
        return null;
    }

}
