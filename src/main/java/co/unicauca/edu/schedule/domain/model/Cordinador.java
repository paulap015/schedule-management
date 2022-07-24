package co.unicauca.edu.schedule.domain.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="coordinador")
public class Cordinador {

    @Id
    @Column(name="us_id")
    private Integer id;
    @Column(name="cor_username")
    private String username;
    @Column(name="cor_fecha_inicio")
    private Date fechaInicio;
}
