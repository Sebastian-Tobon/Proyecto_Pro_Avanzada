package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;

import java.util.List;

public interface CompraServicio {

    List<DetalleCompra> listaProductoComprado(Integer codigo) throws Exception;

}
