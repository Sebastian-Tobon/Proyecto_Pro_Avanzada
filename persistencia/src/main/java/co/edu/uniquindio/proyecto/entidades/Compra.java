package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaCompra;

    @Column(nullable = false)
    private String medioPago;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    private List<DetalleCompra> listaDetallesCompra;

    public Compra(Integer codigo, LocalDate fechaCompra, String medioPago, Usuario usuario) {
        this.codigo = codigo;
        this.fechaCompra = fechaCompra;
        this.medioPago = medioPago;
        this.usuario = usuario;
    }
}
