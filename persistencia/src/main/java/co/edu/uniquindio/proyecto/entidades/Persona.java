package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * En la clase Persona se crean los atributos principales que sera heredadas por las otras clases.
 * Integer codigo
 * String nombre
 * String email
 * String password
 *
 * Integrantes:
 * Juan Sebastian Tobon Alcaraz
 * Sebastian Londoño
 * Rodrigo Acosta Restrepo
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@AllArgsConstructor
@ToString
public class Persona implements Serializable {

    @Id
    @Column(length = 10)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    @Length(max = 100)
    @NotBlank
    private String nombre;

    @Column(nullable = false, unique = true)
    @Email(message = "Ingrese un Email Válido")
    @NotBlank
    private String email;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String password;

}
