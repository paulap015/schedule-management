package co.unicauca.edu.schedule.service;

import java.text.ParseException;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.utils.ConvertHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.unicauca.edu.schedule.dao.IPeriodoAcademicoRepository;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademico;

import static java.time.temporal.ChronoUnit.MONTHS;

@Service
public class PeriodoAcademicoServiceImpl implements IPeriodoAcademicoService {

    @Autowired
    private IPeriodoAcademicoRepository periodoAcademicoRepository;

    @Autowired
    private IPAAService paaService;

    @Override
    public List<PeriodoAcademico> findAll() {
        return this.periodoAcademicoRepository.findAll();
    }


    @Override
    public Optional<PeriodoAcademico> findById(int id) {
        System.out.println("Buscar en Service: " + id);
        return this.periodoAcademicoRepository.findById(id);
    }

    @Override
    public PeriodoAcademico save(PeriodoAcademico periodo) {
        ConvertHour convertidor = new ConvertHour();
        try {
            if(validarFecha(convertidor.stringToDateD(periodo.getFechaInicio()),convertidor.stringToDateD(periodo.getFechaFin()))==false){
                System.out.println("Las fechas no son correctas");
                //return null;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        periodo.setEstado(true);
        //logica del periodo academico 3 o 6 meses
        return this.periodoAcademicoRepository.save(periodo);   
    }
    private boolean validarFecha(Date inicial, Date fin){
        int difA = fin.getYear()- inicial.getYear();
        int difM = difA *12 + fin.getMonth()-inicial.getMonth();

        System.out.println("apunto de ver vlaidaciones "+inicial.before(fin) +" and "+(inicial));
        if(inicial.before(fin) && ((fin.getMonth()-inicial.getMonth()==3)||(fin.getMonth()-inicial.getMonth()==6))){
            System.out.println("Las validaciones es correcto");
            return true;
        }
        return false;
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

    @Override
    public boolean periodoAcademicoTieneReferencias(Integer pa) {

        List<PeriodoAcademicoAmbiente> paaAll = paaService.findAllByPa(pa);
        if(paaAll.size() == 0){
            return true;
        }
        return false;
    }

}
