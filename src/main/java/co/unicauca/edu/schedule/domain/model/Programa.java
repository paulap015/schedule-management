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
@Table(name="programa")
public class Programa {
    @Id
    @Column(name="prog_codigo")
    private Integer progCodigo;

    @Column(name="prog_nombre")
    private String nombrePrograma;
}
