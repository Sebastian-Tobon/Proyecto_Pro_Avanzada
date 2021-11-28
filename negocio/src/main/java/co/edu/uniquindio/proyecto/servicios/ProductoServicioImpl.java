package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;
    private final CategoriaRepo categoriaRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final CompraRepo compraRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo, CategoriaRepo categoriaRepo, DetalleCompraRepo detalleCompraRepo, CompraRepo compraRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
        this.categoriaRepo = categoriaRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.compraRepo = compraRepo;
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
        return productoRepo.listarProductosXCategoria(categoria);
    }

    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDateTime.now());
        comentarioRepo.save(comentario);
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

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategorias(Integer codigo) throws Exception {
        return categoriaRepo.findById(codigo).orElseThrow(() -> new Exception("El Id no Corresponde a ninguna Categoria"));
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioDePago) throws Exception {
        try {
            Compra c = new Compra();
            c.setFechaCompra(LocalDateTime.now(ZoneId.of("America/Bogota")));
            c.setUsuario(usuario);
            c.setMedioPago(medioDePago);

            Compra compraGuardada = compraRepo.save(c);

            DetalleCompra dc;
            for (ProductoCarrito p : productos) {
                dc = new DetalleCompra();
                dc.setCompra(compraGuardada);
                dc.setPrecioProducto(p.getPrecio());
                dc.setUnidades(p.getUnidades());
                dc.setProducto(productoRepo.findById(p.getId()).get());
                //Verificar que las unidades del producto si estén disponibles
                detalleCompraRepo.save(dc);
            }
            return  compraGuardada;
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}
