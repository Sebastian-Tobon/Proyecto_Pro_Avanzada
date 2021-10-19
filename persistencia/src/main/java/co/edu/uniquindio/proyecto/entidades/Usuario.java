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
public class Usuario extends Persona implements Serializable {

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

    @ManyToMany(mappedBy = "usuarios")
    private List<Producto> productos;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> numTelefono;

    public Usuario(Integer codigo, String nombre, String email, String password, Ciudad ciudad, Map<String, String> numTelefono) {
        super(codigo, nombre, email, password);
        this.ciudad = ciudad;
        this.numTelefono = numTelefono;
    }
}
