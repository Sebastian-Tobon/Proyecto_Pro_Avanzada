package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(nullable = false)
    @Positive
    private Integer unidades;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    @Positive
    private Integer precio;

    @Column(nullable = false)
    @Future
    private LocalDateTime fecha_limite;

    public Producto(String nombre, Integer unidades, String descripcion, Integer precio, LocalDateTime fecha_limite) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha_limite = fecha_limite;
    }
}
