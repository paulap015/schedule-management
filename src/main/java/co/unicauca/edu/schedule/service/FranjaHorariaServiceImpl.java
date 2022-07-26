package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IFranjaHorariaRepository;
import co.unicauca.edu.schedule.dao.IPAARepository;
import co.unicauca.edu.schedule.domain.model.Competencia;
import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.dto.FranjaResponseDTO;
import co.unicauca.edu.schedule.utils.ConvertHour;
import co.unicauca.edu.schedule.utils.DTOtoClass;
import co.unicauca.edu.schedule.utils.Validar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


        FranjaHoraria newFran= franjaRepository.save(franja);
        paaService.save(franjaDTO,newFran); //creando nuevo periodo academico ambiente
        return newFran;
    }



    @Override
    public boolean deleteFranja(FranjaHoraria franja) {
        paaService.delete(paaService.findByHor(franja.getIdHorario()));
        Docente doc=docenteService.findById(franja.getIdDocente().getId());
        doc.setHoras(doc.getHoras()-2);
        franjaRepository.delete(franja);
        return true;
    }

    @Override
    public FranjaHoraria update(FranjaDTO franjaDTO) throws ParseException {

        FranjaHoraria fran = findById(franjaDTO.getIdHorario()).orElse(null);
        Docente doc = docenteService.findById(franjaDTO.getIdDocente());
        Competencia comp = competenciaService.findById(franjaDTO.getCodigoCompetencia()).orElse(null);
        boolean bandera = false;
        if(comp==null || doc==null || fran ==null){
            return null;
        }
        if(!validar.validarFranja(franjaDTO, doc)){
            return null;
        }
        if(doc.getHoras()>0){
            bandera=true;
            doc.setHoras(doc.getHoras()-2);
        }
        if(!docenteService.canSaveHours(franjaDTO, doc)){ // el docente puede acumular mas horas ?
            System.out.println("El docente ya cumplio con sus horas semanales o diarias ");
            if(bandera)doc.setHoras(doc.getHoras()+2);
            return null;
        }
        doc.setHoras(doc.getHoras()+2);
        docenteService.save(doc);
        fran.setDia(franjaDTO.getDia());
        fran.setCodigoCompetencia(comp);
        fran.setHoraInicio(franjaDTO.getHoraInicio());
        fran.setHoraFin(franjaDTO.getHoraFin());
        fran.setIdHorario(franjaDTO.getIdHorario());

        FranjaHoraria newFran= franjaRepository.save(fran);
        paaService.update(franjaDTO,newFran); //creando nuevo periodo academico ambiente
        return newFran;
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
    @Override
    public List<FranjaDTO> todoHorarioDocente(String idDocente, Integer idPa){
        List<FranjaDTO> horario= new ArrayList<>();

        // obtener toda la franja horaria del docente
        List<FranjaHoraria> franjas = franjaRepository.findByIdDocente(idDocente);
        //obteer todos los paa
        List<PeriodoAcademicoAmbiente> paaAll= paaService.findAllByPa(idPa);//paaService.findAll();
        //con cada id de horario sacar los paa y asignarle dia,horaini,horafin
        for(PeriodoAcademicoAmbiente paa: paaAll){
            for(FranjaHoraria franja:franjas){
                if(paa.getHor().getIdHorario() == franja.getIdHorario() ){
                    FranjaDTO dtoFranja = util.classToFranjaDTO(franja,paa);
                    horario.add(dtoFranja);
                }
            }
        }
        return horario;
    }

    @Override
    public List<FranjaResponseDTO> listFranjaResponse(List<FranjaHoraria> franjas) {
        List<FranjaResponseDTO> dtoList=new ArrayList<>();
        for(FranjaHoraria franja : franjas){
            FranjaResponseDTO dto = util.classToFranjaResponse(franja);
            dtoList.add(dto);
        }
        return dtoList;
    }

}
