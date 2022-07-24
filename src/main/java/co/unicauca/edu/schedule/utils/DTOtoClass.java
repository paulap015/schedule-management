package co.unicauca.edu.schedule.utils;

import co.unicauca.edu.schedule.domain.model.*;
import co.unicauca.edu.schedule.dto.DocenteDTO;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class DTOtoClass {

    public Docente dtoDoc(DocenteDTO dto, Area area, Programa programa,Usuario user) {
        Docente doc = new Docente();
        //doc.setId(user);
        doc.setId(dto.getId());
        /*

        doc.setApellido(dto.getApellido());
        doc.setRol(dto.getRol());
        doc.setNombre(dto.getNombre());
        doc.setTipoId(dto.getTipoId());

         */
        doc.setTipoContrato(dto.getTipoContrato());
        doc.setAreaId(area);

        doc.setTipoDocente(dto.getTipoDocente());
        doc.setProgCodigo(programa);

        return doc;
    }
    public Usuario dtoUsuario(DocenteDTO doc){
        Usuario usuario = new Usuario();
        usuario.setId(doc.getId());
        usuario.setTipoId(doc.getTipoId());
        usuario.setNombre(doc.getNombre());
        usuario.setApellido(doc.getApellido());
        usuario.setPassword(doc.getPassword());
        usuario.setUsername(doc.getUsername());
        usuario.setRol(doc.getRol());

        return usuario;
    }
    public FranjaHoraria dtoFranja(FranjaDTO franjaDTO,Docente doc , Competencia comp) throws ParseException {
        FranjaHoraria franja = new FranjaHoraria();
        ConvertHour convertidor = new ConvertHour();
        franja.setDia(franjaDTO.getDia());
        //franja.setDisponible(franjaDTO.getDisponible());
        franja.setCodigoCompetencia(comp);
        franja.setIdDocente(doc);
        franja.setHoraInicio(convertidor.stringToDate(franjaDTO.getHoraInicio()));
        franja.setHoraFin(convertidor.stringToDate(franjaDTO.getHoraFin()));
        franja.setIdHorario(franjaDTO.getIdHorario());
        return franja;
    }

}
