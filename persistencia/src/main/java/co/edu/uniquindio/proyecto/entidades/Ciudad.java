package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 80)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuarios;

    public Ciudad(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
