package co.unicauca.edu.schedule.domain.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hor_id")
    private Integer idHorario;

    @ManyToOne
    @JoinColumn(name="comp_codigo", nullable=false)
    private Competencia codigoCompetencia;
    @ManyToOne
    @JoinColumn(name="us_id", nullable=false)
    private Docente idDocente;

    @Column(name="hor_hora_inicio")
    private String horaInicio;

    @Column(name="hor_hora_fin")
    private String horaFin;

    @Column(name="hor_dia")
    private String dia;

    @Column(name="hor_disponible")
    private Boolean disponible;

}
