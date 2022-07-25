package co.unicauca.edu.schedule.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="docente")
public class Docente implements  Serializable {

    @Id
    @Column(name="us_id")
    private String id;

    @ManyToOne
    @JoinColumn(name="area_id", nullable=false)
    private Area areaId;

    @ManyToOne
    @JoinColumn(name="prog_codigo", nullable=false)
    private Programa progCodigo;
    @Column(name="doc_tipo_docente")
    private String tipoDocente;

    @Column(name="doc_tipo_contrato")
    private String tipoContrato;

    @Column(name="doc_horas_realizadas")
    private Integer horas;

    @Column(name="doc_is_available")
    private Boolean available;
}
