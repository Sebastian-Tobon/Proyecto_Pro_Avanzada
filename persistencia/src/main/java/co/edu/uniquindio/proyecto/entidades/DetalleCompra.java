package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class DetalleCompra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @Positive
    private Integer unidades;

    @Column(nullable = false)
    private Double precioProducto;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Compra compra;

    public DetalleCompra(Integer codigo, Integer unidades, Double precioProducto, Producto producto, Compra compra) {
        this.codigo = codigo;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.producto = producto;
        this.compra = compra;
    }
}
