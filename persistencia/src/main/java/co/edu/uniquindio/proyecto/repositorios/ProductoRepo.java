package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.dto.ProductosXUsuario;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    Page<Producto> findAll(Pageable paginador);

    @Query("select p from Producto p where :categoria member of p.categorias")
    List<Producto> listarProductosXCategoria(Categoria categoria);

    @Query("select p.vendedor.nombre from Producto p where p.codigo = :id")
    String obtenerNombreVendedor(Integer id);

    @Query("select c from Comentario c where c.producto.codigo= :id")
    List<Comentario> obtenerComentarios(Integer id);

    @Query("select p.nombre, c from Producto p left join p.listaComentarios c")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.usuario from Producto p join p.listaComentarios c where p.codigo = :id")
    List<Usuario> listarUsuariosComentarios(Integer id);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido(p.nombre, p.descripcion, p.precio, p.ciudad) from Producto p where :fechaActual < p.fecha_limite")
    List<ProductoValido> listarProductosValidos(LocalDateTime fechaActual);

    //Cree una consulta que permita contar el número de productos que hay por cada tipo de producto.
    //Use GROUP BY.
    @Query("select c.nombre, count(p) from Producto p join p.categorias c group by c")
    List<Object[]> obtenerTotalProductosXCategoria();

    //Cree una consulta que permita determinar que productos no tiene comentarios. Use IS EMPTY
    @Query("select p from Producto p where p.listaComentarios is empty ")
    List<Producto> obtenerProductosSinComentarios();

    // Cree una consulta que devuelva una lista con todos los productos que contengan en su nombre una
    //cadena de búsqueda. Use LIKE.
    List<Producto> findByNombreContains(String nombre);
    //Usando Query para buscar productos por nombre
    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%')")
    List<Producto> buscarProductoXNombre(String nombre);

    //Cree un consulta que permita determinar cuántos productos ha publicado a la venta cada usuario.
    //Devuelva un DTO con cédula del usuario, el email y número de registros.
    @Query("select new co.edu.uniquindio.proyecto.dto.ProductosXUsuario(p.vendedor.codigo, p.vendedor.email, count(p)) from Producto p group by p.vendedor")
    List<ProductosXUsuario> obtenerProductosEnVenta();

    //Cree una consulta que permita determinar cuál es el tipo de producto que tiene más registros.HAcer test
    @Query("select c, count (p) as total from Producto  p join p.categorias c group by c order by total desc")
    List<Object[]> obtenerCategoriaMasUsada();

    //Escriba una consulta que retorne la calificación promedio de un producto. Hacer Test
    @Query("select avg(c.calificacion) from Producto  p join p.listaComentarios c where p.codigo = :codigo")
    Float obtenerPromedioCalificaciones(Integer codigo);
}
