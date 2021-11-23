package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;

import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException;

    List<Producto> listarProductos(Categoria categoria);

    List<Producto> listarTodosProductos();

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario)throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario)throws Exception;

    void comprarProductos(Compra compra)throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductos(Integer codigoUsuario)throws Exception;

    List<Categoria> listarCategorias();

    Categoria obtenerCategorias(Integer codigo) throws Exception;
}
