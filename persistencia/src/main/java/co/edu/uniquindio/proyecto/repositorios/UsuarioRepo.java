package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.UsuarioYProducto;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    //@Query ("select  u from Usuario  u where  u.nombre = :nombre")
    //List<Usuario> obtenerUsuarioPorNombre(String nombre);

    List<Usuario> findAllByNombreContains(String nombre);  //Todos los Usuarios por nombre dado

    //Traer un Usuario por su Correo Electronico
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailAndPassword(String email, String clave);

    Page<Usuario> findAll(Pageable paginador);

    @Query("select p from Usuario u, IN (u.usuarioListProductosFav) p where u.email = :email")
    List<Producto> obtenerProductoFavoritos(String email);

    @Query("select new co.edu.uniquindio.proyecto.dto.UsuarioYProducto(u.email, u.nombre, p )from Usuario u join u.listaProductosVenta p")
    List<UsuarioYProducto> listarUsuariosYProductos();
}
