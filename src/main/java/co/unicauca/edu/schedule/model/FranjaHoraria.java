package co.unicauca.edu.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="franjahoraria")
public class FranjaHoraria {

    @Id
    @Column(name="hor_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="comp_codigo", nullable=false)
    //@Column(name="comp_codigo")
    private Competencia codigoCompetencia;

    @Column(name="us_id")
    private Integer idUsuario;

    @Column(name="hor__hora_inicio")
    private Date horaInicio;

    @Column(name="hor_hora_fin")
    private Date horaFin;

    @Column(name="hor_dia")
    private String dia;

    @Column(name="hor_disponible")
    private Boolean disponible;

}
