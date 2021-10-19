package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    private List<Chat> listaChats;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> listaComentarios;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> listaCompras;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> listaProductos;

    @OneToMany(mappedBy = "usuario")
    private List<DetalleSubasta> listaDetalleSubasta;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> numTelefono;
}
