package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    //Cree una consulta que devuelva la oferta más alta para una subasta. Realizar Test
    @Query("select  max(d.valor) from Subasta  s join s.detalleSubasta d where s.codigo = :codigo")
    Float obtenerValorMasAlto(Integer codigo);

    //Cree una consulta que devuelva una lista de subastas que aún están disponibles. Realizar Test
    @Query("select  s from Subasta s where current_timestamp  < s.fecha_limite")
    List<Subasta> listarSubastasDisponibles();
}
