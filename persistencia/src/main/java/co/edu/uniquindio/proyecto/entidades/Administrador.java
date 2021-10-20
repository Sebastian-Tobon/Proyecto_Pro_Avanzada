package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable{

    public Administrador(Integer codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
