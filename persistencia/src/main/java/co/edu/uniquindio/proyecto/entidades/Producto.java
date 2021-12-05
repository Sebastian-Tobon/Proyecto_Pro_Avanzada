package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * En la clase Producto se prentende gestionar todo lo relacionado con los productos
 * Integer codigo
 * String nombre
 * Integer unidades
 * String descripcion
 * Integer precio
 * LocalDateTime fecha_limite
 * Float descuento
 * Usuario usuario
 * Ciudad ciudad
 *
 *
 * Integrantes:
 * Juan Sebastian Tobon Alcaraz
 * Sebastian Londoño
 * Rodrigo Acosta Restrepo
 */

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

    @NotBlank(message = "El nombre del Producto es Obligatorio")
    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(nullable = false)
    @PositiveOrZero
    private Integer unidades;

    @NotBlank
    @Column(nullable = false)
    private String nombrePublicacion;       //nuevo

    @Column(nullable = false)
    @NotBlank
    private String descripcion;

    @Column(nullable = false)
    @Positive
    private Integer precio;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Future
    private LocalDateTime fecha_limite;

    @Column(nullable = false)
    @Positive
    private Float descuento;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> listaComentarios;

    @OneToMany(mappedBy = "codigo_producto")
    @ToString.Exclude
    private List<Chat> listaChats;

    @ElementCollection
    @Column(nullable = false)
    private List<String> listaImagenes;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> listaSubastas;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> listaDetallesCompra;

    @ManyToMany(mappedBy = "productos")
    @ToString.Exclude
    private List<Categoria> categorias;

   // @ElementCollection
   // @Column(nullable = false)
   // private List<Categoria> categorias;

    @ManyToMany(mappedBy = "usuarioListProductosFav")       //nuevo
    @ToString.Exclude
    private List<Usuario> prodListUsuarioProdFav;

    /**
     *
     * @param nombre
     * @param unidades
     * @param nombrePublicacion
     * @param descripcion
     * @param precio
     * @param fecha_limite
     * @param descuento
     * @param vendedor
     */
    public Producto(String nombre, Integer unidades, String nombrePublicacion, String descripcion, Integer precio, LocalDateTime fecha_limite, Float descuento, Usuario vendedor) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.nombrePublicacion = nombrePublicacion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha_limite = fecha_limite;
        this.descuento = descuento;
        this.vendedor = vendedor;
    }

    public String getImagenPrincipal(){
        if (listaImagenes != null && !listaImagenes.isEmpty()){
            return listaImagenes.get(0);
        }
        return "default.jpg";
    }

}
