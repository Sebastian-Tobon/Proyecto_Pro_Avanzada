package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String respuesta;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaComentario;

    @Column(nullable = false)
    private Float calificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    public Comentario(Integer codigo, String mensaje, String respuesta, LocalDateTime fechaComentario, Float calificacion, Producto producto, Usuario usuario) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fechaComentario = fechaComentario;
        this.calificacion = calificacion;
        this.producto = producto;
        this.usuario = usuario;
    }
}
