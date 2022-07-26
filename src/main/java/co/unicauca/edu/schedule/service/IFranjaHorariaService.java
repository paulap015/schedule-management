package co.unicauca.edu.schedule.service;


import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.FranjaHoraria;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademicoAmbiente;
import co.unicauca.edu.schedule.dto.FranjaDTO;
import co.unicauca.edu.schedule.dto.FranjaResponseDTO;
import org.springframework.data.repository.query.Param;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface IFranjaHorariaService {

    public List<FranjaHoraria> findAll();
    public Optional<FranjaHoraria> findById(int id);

    public FranjaHoraria save(FranjaDTO franja) throws ParseException;

    //public void deleteById(int id);

    public boolean deleteFranja(FranjaHoraria franja);
    public FranjaHoraria update(FranjaDTO franja) throws ParseException;

    public List<FranjaHoraria> allScheduleDoc(String id);

    public int horasDiaDocente( String dia, String doc);

    public List<FranjaHoraria> franjasDocenteDiaOcupado( String dia,String doc);
    public List<FranjaHoraria> franjasHoraDiaOcupado( String dia, String horaIni);

    public List<FranjaDTO> todoHorarioDocente(String idDocente,Integer idPa);

    public List<FranjaResponseDTO> listFranjaResponse(List<FranjaHoraria> franjas);
}
