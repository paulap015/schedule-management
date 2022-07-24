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
@Table(name="periodoacademicoambiente")
public class PeriodoAcademicoAmbiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="paa_id")
    private Integer idPaa;
    @ManyToOne
    @JoinColumn(name="amb_codigo")
    private Ambiente ambienteCod;
    @ManyToOne
    @JoinColumn(name="pa_id")
    private PeriodoAcademico paId;
    @ManyToOne
    @JoinColumn(name="hor_id")
    private FranjaHoraria hor;
}
