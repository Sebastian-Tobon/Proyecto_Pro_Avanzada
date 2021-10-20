package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario_comprador;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto codigo_producto;

    @OneToMany(mappedBy = "chat")
    @ToString.Exclude
    private List<Mensaje> listaMansajes;

    public Chat(Integer codigo, Usuario usuario_comprador, Producto codigo_producto) {
        this.codigo = codigo;
        this.usuario_comprador = usuario_comprador;
        this.codigo_producto = codigo_producto;
    }
}
