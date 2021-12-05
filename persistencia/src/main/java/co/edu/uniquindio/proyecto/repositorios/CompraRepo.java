package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductosXUsuario;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
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
public interface CompraRepo extends JpaRepository<Compra, Integer> {
    //Cree una consulta que permita determinar el número de productos que ha comprado un usuario. No
    //repita productos en la cuenta. Use COUNT.
    @Query("select distinct count(distinct d.producto) from Compra c join c.listaDetallesCompra d where c.usuario.codigo = :codigo")
    Long obtenerListaProductosComprados(Integer codigo);

    //Cree una consulta que calcule el valor total que ha gastado un usuario en compras y otra que
    //calcule el valor total que ha ganado un usuario por vender sus productos.Hacer test
    @Query("select sum(d.precioProducto*d.unidades) from DetalleCompra  d  where d.producto.vendedor.codigo = : codigo")
    Long calcularTotalVentas(Integer codigo);

    @Query("select sum(d.precioProducto*d.unidades) from Compra c join c.listaDetallesCompra  d  where c.usuario.codigo = : codigo")
    Long calcularTotalCompras(Integer codigo);

    //Cree una consulta que devuelva una lista de Compras y el detalle de cada compra de un cliente
    //dado su código. dto compra, detalle
    @Query("select  d.producto from Compra c join c.listaDetallesCompra d join d.producto p where c.usuario.codigo = : codigo")
    List<Producto> obtenerComprasXUsuario(Integer codigo);

    @Query("select ld from Usuario u join u.listaCompras lc join lc.listaDetallesCompra ld join ld.producto p where u.codigo = :codigo")
    List<DetalleCompra> obtenerListaComprasXUsuario(Integer codigo);

}
