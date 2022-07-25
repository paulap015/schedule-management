package co.unicauca.edu.schedule.utils;

import co.unicauca.edu.schedule.dao.IPAARepository;
import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.service.IDocenteService;
import co.unicauca.edu.schedule.service.IFranjaHorariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
@Component
public class Validar {

    @Autowired
    private ConvertHour convertidor;
    @Lazy
    @Autowired
    private IDocenteService docenteService;

    @Lazy
    @Autowired
    private IFranjaHorariaService franjaService;

    @Lazy
    @Autowired
    private IPAARepository  paaRepository;
    /*
        va a validar logica de la franja : hora tiene sentido, el maestro esta ocupado en esa hora, el ambiente esta ocupado

     */

    public boolean validarFranja(FranjaDTO franjaDTO, Docente doc) throws ParseException {

        //validar que la hora tenga sentido
        try {
            if(validarFecha(convertidor.stringToDateH(franjaDTO.getHoraInicio()),convertidor.stringToDateH(franjaDTO.getHoraFin()))==false){
                System.out.println("Las horas no son correctas");
                return false;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // el docente esta ocupado a esa hora ?
        if(docenteOcupadoFranja(franjaDTO.getDia(), doc.getId(), franjaDTO.getHoraInicio()) ){
            System.out.println("El docente esta ocupado a esa hora "+franjaDTO.getHoraInicio());
            return false;
        }

        // el ambiente esta ocupado a esa hora?
        if(!ambienteOcupadoFranja(franjaDTO.getDia(),franjaDTO.getHoraInicio(),franjaDTO.getAmbienteCod())){
            System.out.println("todo esta bien pero el ambiente esta ocupado en ese horairo");
            return false;
        }
        return true;
    }

    private boolean validarFecha(Date inicial, Date fin){

        //System.out.println("apunto de ver vlaidaciones "+inicial.before(fin) +" and "+(Double.valueOf(fin.getHours())-Double.valueOf(inicial.getHours())));
        if(inicial.before(fin) && (fin.getHours()-inicial.getHours()==2)){
            System.out.println("Las validaciones de fecha es correcto ");
            return true;
        }
        return false;


    }
    private Boolean docenteOcupadoFranja(String dia, String doc,String hora ) throws ParseException {


        List<FranjaHoraria> horarioOcupado = franjaService.franjasDocenteDiaOcupado(dia,doc);

        for(FranjaHoraria franja : horarioOcupado){
            if(!validarHoras(convertidor.stringToDateH(franja.getHoraInicio()), convertidor.stringToDateH(franja.getHoraFin()),
                    convertidor.stringToDateH(hora))){
                return true; //si esta ocupado ese dia a esa hora
            }
        }
        return false; //no esta ocupado a esa hora
    }
    private Boolean ambienteOcupadoFranja(String dia, String hora, String ambiente){
        //retornar todas las franjas que tengan esa hora de inicio en ese dia
        List<FranjaHoraria> franjasOcupadasHora =franjaService.franjasHoraDiaOcupado(dia,hora) ;
        //los todos los p academicos ambientes
        List<PeriodoAcademicoAmbiente> paaAll= paaRepository.findAll();
        for(PeriodoAcademicoAmbiente paa: paaAll){
            for(FranjaHoraria franja:franjasOcupadasHora){
                if(paa.getHor().getIdHorario() == franja.getIdHorario() && paa.getAmbienteCod().getCodigo().equalsIgnoreCase(ambiente)){
                    //el ambiente esta ocupado en ese horario por lo tanto F
                    return false;
                }
            }
        }
        return true;
    }
    private boolean validarHoras(Date horaIni, Date horaFin, Date hora){
        System.out.println("hora ini "+horaIni+" fin "+horaFin+" hora"+hora);
        if( (hora.before(horaFin) && hora.after(horaIni)) || (hora.equals(horaIni)) ){
            return false;
        }
        return true;
    }
}
