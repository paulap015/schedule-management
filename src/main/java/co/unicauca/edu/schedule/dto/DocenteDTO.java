package co.unicauca.edu.schedule.dto;

import co.unicauca.edu.schedule.domain.model.Area;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocenteDTO {

    private String id;
    private String tipoId;
    private String nombre;
    private String apellido;
    private String rol;
    private String password;
    private String username;

    private Integer areaId;
    private Integer progCodigo;
    private String tipoDocente;
    private String tipoContrato;


}
