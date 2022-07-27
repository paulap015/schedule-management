package co.unicauca.edu.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranjaDTO {

    private Integer paaId; //id de periodoAcademicoAmbiente
    private Integer codigoCompetencia;
    private String idDocente;
    private String horaInicio;
    private String horaFin;
    private String dia;
    //private Boolean disponible;

    private String ambienteCod;
    private Integer paId; //id periodo academico
    private Integer idHorario;

}
