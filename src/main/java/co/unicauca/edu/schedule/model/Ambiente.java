package co.unicauca.edu.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="ambiente")
public class Ambiente {

    @Id
    private String codigo;
    @Column(name="AMB_nombre")
    private String nombre;
    @Column(name="AMB_tipo_ambiente")
    private String tipoAmbiente;
    @Column(name="AMB_capacidad")
    private Integer capacidad;
    @Column(name="AMB_ubicacion")
    private String ubicacion;

}
