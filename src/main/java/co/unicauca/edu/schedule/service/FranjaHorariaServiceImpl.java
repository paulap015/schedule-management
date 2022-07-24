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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public FranjaHoraria save(FranjaDTO franjaDTO) throws ParseException {

        Optional<Competencia> comp = competenciaService.findById(franjaDTO.getCodigoCompetencia());
        Docente doc = docenteService.findById(franjaDTO.getIdDocente());
        ConvertHour convertidor = new ConvertHour();
        //validar que competencia docente existan
        if(comp==null){
            return null;
        }
        if(doc==null){
            return null;
        }
        //validar que la hora tenga sentido
        try {
            if(validarFecha(convertidor.stringToDate(franjaDTO.getHoraInicio()),convertidor.stringToDate(franjaDTO.getHoraFin()))==false){
                System.out.println("Las horas no son correctas");
                return null;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //validar que el maestro no este ocupado a esa hora y ese dia
        FranjaHoraria franja = util.dtoFranja(franjaDTO,doc,comp.get()); //obj completo de franja
        franja.setCodigoCompetencia(comp.get());
        franja.setIdDocente(doc);
        franja.setDisponible(false);
        FranjaHoraria newFran= franjaRepository.save(franja);
        paaService.save(franjaDTO,newFran); //creando nuevo periodo academico ambiente
        return newFran;
    }
    private boolean validarFecha(Date inicial, Date fin){

        System.out.println("apunto de ver vlaidaciones "+inicial.before(fin) +" and "+(Double.valueOf(fin.getHours())-Double.valueOf(inicial.getHours())));
        if(inicial.before(fin) && (fin.getHours()-inicial.getHours()==2)){
            System.out.println("Las validaciones es correcto");
            return true;
        }
        return false;
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
}
