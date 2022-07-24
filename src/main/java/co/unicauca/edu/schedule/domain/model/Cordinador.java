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
@Table(name="cordinador")
public class Cordinador implements Serializable {
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTablaCordinador;

    @ManyToOne
    @JoinColumn(name="us_id") */
    @Id
    @Column(name="us_id")
    private String id;
    @Column(name="cor_username")
    private String username;
    @Column(name="cor_fecha_inicio")
    private Date fechaInicio;
}
