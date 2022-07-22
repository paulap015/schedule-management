package co.unicauca.edu.schedule.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @Column(name="us_id")
    private Integer id;

    @Column(name="us_tipo_id")
    private String tipoId;
    @Column(name="us_nombre")
    private String nombre;
    @Column(name="us_apellido")
    private String apellido;
    @Column(name="us_rol")
    private String rol;
    @Column(name="us_password")
    private String password;
    @Column(name="username")
    private String username;
}
