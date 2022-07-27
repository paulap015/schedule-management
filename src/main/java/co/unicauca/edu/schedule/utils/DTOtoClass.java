package co.unicauca.edu.schedule.utils;

import co.unicauca.edu.schedule.domain.model.*;
import co.unicauca.edu.schedule.dto.DocenteDTO;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.dto.FranjaResponseDTO;
import co.unicauca.edu.schedule.service.IDocenteService;
import co.unicauca.edu.schedule.service.IPAAService;
import co.unicauca.edu.schedule.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class DTOtoClass {
    @Lazy
    @Autowired
    private IPAAService paaService;
    @Lazy
    @Autowired
    private IUsuarioService docenteService;

    public Docente dtoDoc(DocenteDTO dto, Area area, Programa programa,Usuario user) {
        Docente doc = new Docente();
        //doc.setId(user);
        doc.setId(dto.getId());
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
        //franja.setHoraInicio(convertidor.stringToDateH(franjaDTO.getHoraInicio()));
        //franja.setHoraFin(convertidor.stringToDateH(franjaDTO.getHoraFin()));
        franja.setHoraInicio(franjaDTO.getHoraInicio());
        franja.setHoraFin(franjaDTO.getHoraFin());
        franja.setIdHorario(franjaDTO.getIdHorario());
        return franja;
    }

    public FranjaDTO classToFranjaDTO(FranjaHoraria franja,PeriodoAcademicoAmbiente paa){
        FranjaDTO dto = new FranjaDTO();
        dto.setPaaId(paa.getIdPaa());
        dto.setCodigoCompetencia(franja.getCodigoCompetencia().getCodigo());
        dto.setIdDocente(franja.getIdDocente().getId());
        dto.setHoraInicio(franja.getHoraInicio());
        dto.setHoraFin(franja.getHoraFin());
        dto.setDia(franja.getDia());
        dto.setAmbienteCod(paa.getAmbienteCod().getCodigo());
        dto.setPaId(paa.getPaId().getId());
        dto.setIdHorario(paa.getHor().getIdHorario());

        return dto;
    }

    public FranjaResponseDTO classToFranjaResponse(FranjaHoraria franja){
        PeriodoAcademicoAmbiente paa= paaService.findByHor(franja.getIdHorario());
        FranjaResponseDTO dto = new FranjaResponseDTO();
        dto.setIdHorario(franja.getIdHorario());
        dto.setPaaId(paa.getIdPaa());
        dto.setPaId(paa.getPaId().getId());
        dto.setPaNombre(paa.getPaId().getNombre());
        dto.setCodigoCompetencia(franja.getCodigoCompetencia().getCodigo());
        dto.setIdDocente(franja.getIdDocente().getId());
        dto.setHoraInicio(franja.getHoraInicio());
        dto.setHoraFin(franja.getHoraFin());
        dto.setDia(franja.getDia());
        dto.setNombreDocente(docenteService.findById(franja.getIdDocente().getId()).getNombre());
        dto.setAmbienteCod(paa.getAmbienteCod().getCodigo());
        return dto;
    }

}
