package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Subasta {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Id
    private Integer codigo;

    @Column(nullable = false)
    @Future
    private LocalDateTime fecha_limite;

    public Subasta(LocalDateTime fecha_limite) {
        this.fecha_limite = fecha_limite;
    }
}
