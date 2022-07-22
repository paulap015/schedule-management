package co.unicauca.edu.schedule.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name = "comp_tipo")
    private String tipoComp;
    @Column(name="comp_nombre")
    private String nombreComp;
}