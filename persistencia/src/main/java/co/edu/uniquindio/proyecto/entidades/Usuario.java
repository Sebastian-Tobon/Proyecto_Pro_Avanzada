package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * En la clase Usuario se gestionara el objeto segun su rol, usuario o vendedor
 * Integer codigo
 * String nombre
 * String email
 * String password
 * String rol
 * Ciudad ciudad
 * Map<String, String> numTelefono
 * List<Chat> listaChats
 * List<Comentario> listaComentarios
 * List<Compra> listaCompras
 * List<Producto> listaProductos
 *
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
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @ManyToOne
    //@JoinColumn(nullable = false)  //nuevo
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario_comprador")
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> listaChats;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> listaComentarios;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> listaCompras;

    @OneToMany(mappedBy = "vendedor")       //modificado
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> listaProductosVenta;     //modificado

    //@ManyToMany(mappedBy = "usuarios")
    //@ToString.Exclude
    //private List<Producto> productos;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private List<String> numTelefono;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleSubasta> listaDetalleSubasta;       //nuevo

    @Column(nullable = false, unique = true)
    private String username;        //nuevo

    //productos Favoritos
    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> usuarioListProductosFav;      //nuevo

    public Usuario(Integer codigo, String nombre, String email, String password, String username, Ciudad ciudad) {
        super(codigo, nombre, email, password);
        this.username = username;
        this.ciudad = ciudad;
    }

}
