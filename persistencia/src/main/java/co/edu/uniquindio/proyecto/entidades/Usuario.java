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
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false, length = 20)
    private String rol;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario_comprador")
    @ToString.Exclude
    private List<Chat> listaChats;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> listaComentarios;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> listaCompras;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Producto> listaProductos;

    @ManyToMany(mappedBy = "usuarios")
    private List<Producto> productos;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> numTelefono;

    public Usuario(Integer codigo, String nombre, String email, String password, String rol, Ciudad ciudad, Map<String, String> numTelefono) {
        super(codigo, nombre, email, password);
        this.rol = rol;
        this.ciudad = ciudad;
        this.numTelefono = numTelefono;
    }
}
