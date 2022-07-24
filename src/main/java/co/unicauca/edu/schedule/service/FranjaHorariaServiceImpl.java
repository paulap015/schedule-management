package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IFranjaHorariaRepository;
import co.unicauca.edu.schedule.dao.IPAARepository;
import co.unicauca.edu.schedule.domain.model.Competencia;
import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.utils.DTOtoClass;
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
    @Autowired
    private IDocenteService docenteService;
    @Autowired
    private IPAAService paaService;

    @Autowired
    private IPAARepository paaRepository;

    @Autowired
    private DTOtoClass util;
    @Override
    public List<FranjaHoraria> findAll() {
        return franjaRepository.findAll();
    }

    @Override
    public Optional<FranjaHoraria> findById(int id) {

        return franjaRepository.findById(id);
    }

    @Override
    public FranjaHoraria save(FranjaDTO franjaDTO) {

        Optional<Competencia> comp = competenciaService.findById(franjaDTO.getCodigoCompetencia());
        Docente doc = docenteService.findById(franjaDTO.getIdDocente());

        if(comp==null){
            return null;
        }
        if(doc==null){
            return null;
        }
        FranjaHoraria franja = util.dtoFranja(franjaDTO,doc,comp.get());
        franja.setCodigoCompetencia(comp.get());
        franja.setIdDocente(doc);
        FranjaHoraria newFran= franjaRepository.save(franja);
        paaService.save(franjaDTO,newFran);
        return newFran;
    }

    @Override
    public void deleteById(int id) {
        franjaRepository.deleteById(id);
    }

    @Override
    public FranjaHoraria update(FranjaHoraria franja) {
        Optional<FranjaHoraria> fran = findById(franja.getIdHorario());
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
        fran.get().setIdHorario(franja.getIdHorario());

        return franjaRepository.save(fran.get());
    }

    @Override
    public List<PeriodoAcademicoAmbiente> allSchedule(Integer id) {
        return paaRepository.findByHor(id);
    }
}
