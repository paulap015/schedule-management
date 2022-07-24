package co.unicauca.edu.schedule.utils;

import co.unicauca.edu.schedule.domain.model.*;
import co.unicauca.edu.schedule.dto.DocenteDTO;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import org.springframework.stereotype.Component;

@Component
public class DTOtoClass {

    public Docente dtoDoc(DocenteDTO dto, Area area, Programa programa) {
        Docente doc = new Docente();
        doc.setId(dto.getId());
        doc.setApellido(dto.getApellido());
        doc.setRol(dto.getRol());
        doc.setNombre(dto.getNombre());
        doc.setTipoContrato(dto.getTipoContrato());
        doc.setAreaId(area);
        doc.setTipoId(dto.getTipoId());
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
    public FranjaHoraria dtoFranja(FranjaDTO franjaDTO,Docente doc , Competencia comp){
        FranjaHoraria franja = new FranjaHoraria();

        franja.setDia(franjaDTO.getDia());
        franja.setDisponible(franjaDTO.getDisponible());
        franja.setCodigoCompetencia(comp);
        franja.setIdDocente(doc);
        franja.setHoraInicio(franjaDTO.getHoraInicio());
        franja.setHoraFin(franjaDTO.getHoraFin());
        franja.setIdHorario(franjaDTO.getIdHorario());
        return franja;
    }

}
