package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * En esta interface se realiza la relacion con la clase que se pretende realizar las pruebas, extiende de JpaRepository
 * para utilizar los metodos que esta nos facilita como el save.
 *
 * Integrantes:
 * Juan Sebastian Tobon Alcaraz
 * Sebastian Londoño
 * Rodrigo Acosta Restrepo
 */

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {

    Optional<Ciudad> findByNombre(String nombreCiudad);

    @Query("select u from Ciudad c join c.listaUsuarios u where c.nombre = :nombre")
    List<Usuario> listarUsuarios(String nombre);

    //Cree una consulta que retorne la cantidad de usuarios por cada ciudad.
    @Query("select c.nombre, count(u) from Ciudad c join c.listaUsuarios u group by c")
    List<Object[]> obtenerTotalUsuariosXCiudad();

}
