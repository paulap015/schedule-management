package co.unicauca.edu.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranjaResponseDTO {

    private Integer idHorario;
    private Integer paaId; //id de periodoAcademicoAmbiente
    private Integer paId;//id periodo academico
    private String paNombre;
    private Integer codigoCompetencia;
    private String idDocente;
    private String horaInicio;
    private String horaFin;
    private String dia;
    private String nombreDocente;
    private String ambienteCod;
    private String message;

    public FranjaResponseDTO(String message) {
        this.message = message;
    }
}
