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
public class Docente implements Serializable {
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTablaDocente;

    @ManyToOne(optional=false)
    @JoinColumn(name="us_id") */
    @Id
    @Column(name="us_id")
    private String id;
    /*
    @Column(name="us_tipo_id")
    private String tipoId;
    @Column(name="us_nombre")
    private String nombre;
    @Column(name="us_apellido")
    private String apellido;
    @Column(name="us_rol")
    private String rol;
    */
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
}
