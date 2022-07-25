package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IFranjaHorariaRepository;
import co.unicauca.edu.schedule.dao.IPAARepository;
import co.unicauca.edu.schedule.domain.model.Competencia;
import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.utils.ConvertHour;
import co.unicauca.edu.schedule.utils.DTOtoClass;
import co.unicauca.edu.schedule.utils.Validar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    @Autowired
    private Validar validar;

    @Override
    public List<FranjaHoraria> findAll() {
        return franjaRepository.findAll();
    }

    @Override
    public Optional<FranjaHoraria> findById(int id) {

        return franjaRepository.findById(id);
    }

    @Override
    public FranjaHoraria save(FranjaDTO franjaDTO) throws ParseException {

        Competencia comp = competenciaService.findById(franjaDTO.getCodigoCompetencia()).orElse(null);
        Docente doc = docenteService.findById(franjaDTO.getIdDocente());

        //validar que competencia docente existan
        if(comp==null || doc==null ){
            return null;
        }

        //validar toda la logica para franja hora,disponibilidad,ambiente
        if(!validar.validarFranja(franjaDTO, doc)){
            return null;
        }
        if(!docenteService.canSaveHours(franjaDTO, doc)){ // el docente puede acumular mas horas ?
            System.out.println("El docente ya cumplio con sus horas semanales o diarias ");
            return null;
        }
        doc.setHoras(doc.getHoras()+2);
        docenteService.save(doc);
        FranjaHoraria franja = util.dtoFranja(franjaDTO,doc,comp); //obj completo de franja
        franja.setCodigoCompetencia(comp);
        franja.setIdDocente(doc);
        franja.setDisponible(false);

        FranjaHoraria newFran= franjaRepository.save(franja);
        paaService.save(franjaDTO,newFran); //creando nuevo periodo academico ambiente
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
    public List<FranjaHoraria> allScheduleDoc(String id) {
        return franjaRepository.findByIdDocente(id);
    }

    @Override
    public int horasDiaDocente(String dia, String doc) {
        return franjaRepository.horasDiaDocente(dia,doc);
    }

    @Override
    public List<FranjaHoraria> franjasDocenteDiaOcupado(String dia, String doc) {
        return franjaRepository.franjasDocenteDiaOcupado(dia,doc);
    }

    @Override
    public List<FranjaHoraria> franjasHoraDiaOcupado(String dia, String horaIni) {
        return franjaRepository.franjasHoraDiaOcupado(dia,horaIni);
    }


}
