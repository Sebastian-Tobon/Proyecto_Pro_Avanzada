package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {

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

    @OneToMany(mappedBy = "producto")
    private List<Comentario> listaComentarios;

    @ElementCollection
    @Column(nullable = false)
    private List<String> listaImagenes;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    private List<Subasta> listaSubastas;

    @OneToMany(mappedBy = "producto")
    private List<DetalleCompra> listaDetallesCompra;

    @ManyToMany(mappedBy = "productos")
    private List<Categoria> categorias;

    @ManyToMany
    private List<Usuario> usuarios;

    public Producto(String nombre, Integer unidades, String descripcion, Integer precio, LocalDateTime fecha_limite) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha_limite = fecha_limite;
    }
}
