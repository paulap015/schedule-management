package co.unicauca.edu.schedule.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="competencia")
public class Competencia{

    @Id
    @Column(name="comp_codigo")
    private Integer codigo;
    @ManyToOne
    @JoinColumn(name="prog_codigo", nullable=false)
    private Programa progCodigo;
    @Column(name = "comp_tipo")
    private String tipoComp;
    @Column(name="comp_nombre")
    private String nombreComp;
}