package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.ICompetenciaRepository;
import co.unicauca.edu.schedule.model.Competencia;
import co.unicauca.edu.schedule.model.FranjaHoraria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaServiceImpl implements ICompetenciaService{

    @Autowired
    private ICompetenciaRepository competenciaRepository;
    @Override
    public List<Competencia> findAll() {
        return competenciaRepository.findAll();
    }

    @Override
    public Optional<Competencia> findById(int id) {

        return competenciaRepository.findById(id);
    }

    @Override
    public Competencia save(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    @Override
    public void deleteById(int id) {
        competenciaRepository.deleteById(id);
    }

    @Override
    public Competencia update(Competencia competencia) {
        Optional<Competencia> comp = findById(competencia.getCodigo());


        return null;
    }
}
