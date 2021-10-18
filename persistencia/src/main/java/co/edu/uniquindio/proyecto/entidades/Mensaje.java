package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
 
    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String emisor;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    private Chat chat;

    public Mensaje(String mensaje, String emisor, LocalDateTime fecha) {
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.fecha = fecha;
    }
}
