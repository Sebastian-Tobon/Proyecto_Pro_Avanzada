package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface SubastaRepo extends JpaRepository<Subasta, Integer> {
}
