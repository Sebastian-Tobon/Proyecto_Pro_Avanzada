package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;

    Producto actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException;
    Producto obtenerProductoXCodigo(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);


    List<Producto> listarTodosProductos();

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario)throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario)throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductosXUsuario(Integer codigoUsuario)throws Exception;

    List<Categoria> listarCategorias();

    Categoria obtenerCategorias(Integer codigo) throws Exception;

    Float obtenerCalificacionPromedioProducto(Integer codigo);

    Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioDePago) throws Exception;

    List<Producto> obtenerProductoXPrecio(Integer precio, Integer precio2);

    List<Producto> obtenerProductoXPrecio2(Integer precio);

    List<Producto> obtenerProductoXUbicacion(String ciudad);

    List<Producto> listarProductosXNameCategoria(String categoria);

    List<Producto> listaProductoXcalificacion(Integer puntaje);
}
