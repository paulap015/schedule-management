package co.unicauca.edu.schedule.dto;
import co.unicauca.edu.schedule.domain.model.Ambiente;
import co.unicauca.edu.schedule.domain.model.Competencia;
import co.unicauca.edu.schedule.domain.model.Docente;
import co.unicauca.edu.schedule.domain.model.PeriodoAcademico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranjaDTO {

    private Integer paaId; //id de periodoAcademicoAmbiente
    private Integer codigoCompetencia;
    private String idDocente;
    private Date horaInicio;
    private Date horaFin;
    private String dia;
    private Boolean disponible;

    private String ambienteCod;
    private Integer paId; //id periodo academico
    private Integer idHorario;
}
