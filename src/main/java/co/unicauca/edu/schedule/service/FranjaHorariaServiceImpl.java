package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IFranjaHorariaRepository;
import co.unicauca.edu.schedule.model.Ambiente;
import co.unicauca.edu.schedule.model.Competencia;
import co.unicauca.edu.schedule.model.FranjaHoraria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranjaHorariaServiceImpl implements IFranjaHorariaService{

    @Autowired
    private IFranjaHorariaRepository franjaRepository;
    @Autowired
    private ICompetenciaService competenciaService;

    @Override
    public List<FranjaHoraria> findAll() {
        return franjaRepository.findAll();
    }

    @Override
    public Optional<FranjaHoraria> findById(int id) {

        return franjaRepository.findById(id);
    }

    @Override
    public FranjaHoraria save(FranjaHoraria franja) {


        Optional<Competencia> comp = competenciaService.findById(franja.getCodigoCompetencia().getCodigo());
        if(comp==null){
            return null;
        }
        franja.setCodigoCompetencia(comp.get());
        return franjaRepository.save(franja);
    }

    @Override
    public void deleteById(int id) {
        franjaRepository.deleteById(id);
    }

    @Override
    public FranjaHoraria update(FranjaHoraria franja) {
        Optional<FranjaHoraria> fran = findById(franja.getId());
        if (fran.isEmpty()){
            return null;
        }
        Optional<Competencia> comp = competenciaService.findById(franja.getCodigoCompetencia().getCodigo());
        if(comp==null){
            return null;
        }

        fran.get().setDia(franja.getDia());
        fran.get().setDisponible(franja.getDisponible());
        fran.get().setCodigoCompetencia(comp.get());
        fran.get().setHoraInicio(franja.getHoraInicio());
        fran.get().setHoraFin(franja.getHoraFin());
        fran.get().setId(franja.getId());

        return franjaRepository.save(fran.get());
    }
}
