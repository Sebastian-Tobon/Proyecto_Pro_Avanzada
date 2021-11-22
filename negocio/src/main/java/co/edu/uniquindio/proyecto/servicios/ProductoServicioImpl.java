package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try {
            return productoRepo.save(p);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {

    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);

        if (producto.isEmpty()){
            throw new Exception("El código del producto no existe");
        }
        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException {
        return productoRepo.findById(codigo).orElseThrow(() -> new ProductoNoEncontradoException("El codigo del producto no es Valido"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return null;
    }

    @Override
    public void comentarProducto(String mensaje, Float calificacion, Usuario usuario, Producto producto) throws Exception {

    }

    @Override
    public void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {
        return productoRepo.buscarProductoXNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductos(Integer codigoUsuario) throws Exception {
        return null;
    }
}
