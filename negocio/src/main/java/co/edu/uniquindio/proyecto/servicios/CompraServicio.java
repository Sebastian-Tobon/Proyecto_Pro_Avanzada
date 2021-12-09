package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;

import java.util.List;

public interface CompraServicio {

    List<DetalleCompra> listaProductoComprado(Integer codigo) throws Exception;

    Long totalPrecioCompra(Integer codigo);

    List<Producto> listaProductoComprado2(Integer codigo) throws Exception;

}
