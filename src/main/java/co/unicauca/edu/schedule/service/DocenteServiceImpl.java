package co.unicauca.edu.schedule.service;

import co.unicauca.edu.schedule.dao.IDocenteRepository;
import co.unicauca.edu.schedule.domain.model.Area;
import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.Programa;
import co.unicauca.edu.schedule.dto.DocenteDTO;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements  IDocenteService{

    @Autowired
    private IDocenteRepository docenteRepository;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IProgramaService programaService;
    @Lazy
    @Autowired
    private IFranjaHorariaService franjaService;

    @Override
    public Docente save(Docente doc) {
        System.out.println("llega doc "+doc);
        Area area= areaService.findById(doc.getAreaId().getId());
        Programa programa = programaService.findByCodigo(doc.getProgCodigo().getProgCodigo());
        if(area==null){
            System.out.println("Area no existe");
            return null;
        }
        if(programa==null){
            System.out.println("Programa no existe");
            return null;
        }
        doc.setAreaId(area);
        doc.setProgCodigo(programa);
        return docenteRepository.save(doc);
    }


    @Override
    public Docente findById(String id) {
        return docenteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Docente> findAll() {
        return docenteRepository.findAll();
    }

    @Override
    public List<Docente> findAllAvailable() {
        return docenteRepository.findAllAvailable();
    }

    @Override
    public boolean canSaveHours(FranjaDTO franja, Docente doc) {

        //horas en ese dia
        int horasDia = (franjaService.horasDiaDocente(franja.getDia(),doc.getId()))*2;

        if(doc.getTipoContrato().equalsIgnoreCase("CNT")){
            // validacion de horas
            if(horasDia >=10){
                System.out.println("Ya cumplio con las horas ese dia");
                return false;
            }
            if(doc.getHoras() <= 38){ //a la semana 40 horas, puede sumar 2 mas

                return true;
            }
            System.out.println("Ya cumplio con las horas semanales ");

        }else if(doc.getTipoContrato().equalsIgnoreCase("PT")){
            if(horasDia >=8){
                System.out.println("Ya cumplio con las horas ese dia");
                return false;
            }
            if(doc.getHoras() <= 30){ // suma hasta 32 horas a la semana

                return true;
            }
        }
        doc.setAvailable(false); //ya tiene todas sus horas semanales
        return false;
    }
}
