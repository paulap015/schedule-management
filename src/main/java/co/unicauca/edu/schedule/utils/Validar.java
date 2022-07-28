package co.unicauca.edu.schedule.utils;

import co.unicauca.edu.schedule.dao.IPAARepository;
import co.unicauca.edu.schedule.domain.model.Ambiente;
import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.service.IDocenteService;
import co.unicauca.edu.schedule.service.IFranjaHorariaService;
import co.unicauca.edu.schedule.service.IPAAService;
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
    private IPAAService paaService;
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
        if(docenteOcupadoFranja(franjaDTO.getDia(), doc.getId(), franjaDTO.getHoraInicio(),true,null) ){
            System.out.println("El docente esta ocupado a esa hora "+franjaDTO.getHoraInicio());
            return false;
        }

        // el ambiente esta ocupado a esa hora?
        if(ambienteOcupadoFranja(franjaDTO.getDia(),franjaDTO.getHoraInicio(),franjaDTO.getAmbienteCod(),false,null)){
            System.out.println("todo esta bien pero el ambiente esta ocupado en ese horairo");
            return false;
        }
        return true;
    }
    public boolean validarFranjaUpdate(FranjaDTO franjaUpdate, Docente doc , FranjaHoraria franjaActual, Ambiente ambienteActual,PeriodoAcademicoAmbiente paaActual) throws ParseException {

        //validar que la hora tenga sentido
        try {
            if(validarFecha(convertidor.stringToDateH(franjaUpdate.getHoraInicio()),convertidor.stringToDateH(franjaUpdate.getHoraFin()))==false )
            {
                System.out.println("Las horas no son correctas");
                return false;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if(!franjaUpdate.getIdDocente().equalsIgnoreCase(franjaActual.getIdDocente().getId()) ){ // es un docente diferente

            // validar que el docente no este ocupado a esa hora y ademas no sea el mismo en esa actualizacion
            if(docenteOcupadoFranja(franjaUpdate.getDia(), doc.getId(), franjaUpdate.getHoraInicio(),true, franjaActual)){
                System.out.println("El docente esta ocupado a esa hora "+franjaUpdate.getHoraInicio());
                return false;
            }

        }else{
            if(docenteOcupadoFranja(franjaUpdate.getDia(), doc.getId(), franjaUpdate.getHoraInicio(),false, franjaActual)){
                System.out.println("El docente esta ocupado a esa hora "+franjaUpdate.getHoraInicio());
                return false;
            }

        }

        if(!franjaUpdate.getAmbienteCod().equalsIgnoreCase(ambienteActual.getCodigo())){ // son ambientes diferentes
            //System.out.println("SON AMBIENTES DIFERENTES ");
            if(ambienteOcupadoFranja(franjaUpdate.getDia(),franjaUpdate.getHoraInicio(),franjaUpdate.getAmbienteCod(),true,null)){
                System.out.println("todo esta bien pero el ambiente esta ocupado en ese horairo");
                return false;
            }
        }else{
            //System.out.println("SON LOS MISMOS AMBIENTES");
            if(ambienteOcupadoFranja(franjaUpdate.getDia(),franjaUpdate.getHoraInicio(),franjaUpdate.getAmbienteCod(),false,paaActual)){
                System.out.println("todo esta bien pero el ambiente esta ocupado en ese horairo");
                return false;
            }
        }


        return true;
    }

    private boolean validarFecha(Date inicial, Date fin){

        //System.out.println("apunto de ver vlaidaciones "+inicial.before(fin) +" and "+(Double.valueOf(fin.getHours())-Double.valueOf(inicial.getHours())));
        if(inicial.before(fin) && (fin.getHours()-inicial.getHours()==2)){
            //System.out.println("Las validaciones de fecha es correcto ");
            return true;
        }
        return false;


    }
    private Boolean docenteOcupadoFranja(String dia, String doc,String hora,boolean esDiferente ,FranjaHoraria franjaActual ) throws ParseException {

        List<FranjaHoraria> horarioOcupado = franjaService.franjasDocenteDiaOcupado(dia,doc);
        if(esDiferente ==false){

            horarioOcupado.remove(franjaActual); //removiendo asi mismo esa hora
        }
        for(FranjaHoraria franja : horarioOcupado){
            if(!validarHoras(convertidor.stringToDateH(franja.getHoraInicio()), convertidor.stringToDateH(franja.getHoraFin()),
                    convertidor.stringToDateH(hora))){
                return true; //si esta ocupado ese dia a esa hora
            }
        }
        return false; //no esta ocupado a esa hora
    }
    public boolean docenteEsDiferenteUpdate(Docente dto, Docente  actual){

        if(dto.getId().equalsIgnoreCase(actual.getId())){

            return false; // soon los mismos
        }

        return true; // si es diferente
    }
    private Boolean ambienteOcupadoFranja(String dia, String hora, String ambiente, boolean esDiferente , PeriodoAcademicoAmbiente paaActual){
        //retornar todas las franjas que tengan esa hora de inicio en ese dia
        List<FranjaHoraria> franjasOcupadasHora =franjaService.franjasHoraDiaOcupado(dia,hora) ;
        //los todos los p academicos ambientes
        List<PeriodoAcademicoAmbiente> paaAll= paaService.findAll();

        // es el mismo ambiente ? si -> sacar de la lista de paaAll
        if (!esDiferente){ // son diferentes

            paaAll.remove(paaActual);
        }
        for(PeriodoAcademicoAmbiente paa: paaAll){
            for(FranjaHoraria franja:franjasOcupadasHora){
                if(paa.getHor().getIdHorario() == franja.getIdHorario() && paa.getAmbienteCod().getCodigo().equalsIgnoreCase(ambiente)){
                    //el ambiente esta ocupado en ese horario por lo tanto F
                    return true;
                }
            }
        }
        return false;
    }
    private boolean validarHoras(Date horaIni, Date horaFin, Date hora){
        //System.out.println("hora ini "+horaIni+" fin "+horaFin+" hora"+hora);
        if( (hora.before(horaFin) && hora.after(horaIni)) || (hora.equals(horaIni)) ){
            return false;
        }
        return true;
    }
}
