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
@Table(name="ambiente")
public class Ambiente {

    @Id
    @Column(name="amb_codigo")
    private String codigo;
    @Column(name="amb_nombre")
    private String nombre;
    @Column(name="amb_tipo_ambiente")
    private String tipoAmbiente;
    @Column(name="amb_capacidad")
    private Integer capacidad;
    @Column(name="amb_ubicacion")
    private String ubicacion;

}
