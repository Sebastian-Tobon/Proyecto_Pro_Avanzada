package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.dto.ProductoFavorito;
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
    private final UsuarioRepo usuarioRepo;
    private final ComentarioRepo comentarioRepo;
    private final CategoriaRepo categoriaRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final CompraRepo compraRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, UsuarioRepo usuarioRepo, ComentarioRepo comentarioRepo, CategoriaRepo categoriaRepo, DetalleCompraRepo detalleCompraRepo, CompraRepo compraRepo) {
        this.productoRepo = productoRepo;
        this.usuarioRepo = usuarioRepo;
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
    public Producto actualizarProducto(Producto p) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(p.getCodigo());
        if (buscado.isEmpty()){
            throw  new Exception("El Producto no Existe");
        }
        return productoRepo.save(p);
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
    public Producto obtenerProductoXCodigo(Integer codigo) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw  new Exception("El Producto no Existe");
        }
        return buscado.get();
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
        try {
            comentario.setFechaComentario(LocalDateTime.now());
            comentarioRepo.save(comentario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception {
        try {
            Optional<Producto> proBuscado = productoRepo.findById(producto.getCodigo());
            Optional<Usuario> usuBuscado = usuarioRepo.findById(usuario.getCodigo());
            // ProductoFavorito favorito = (ProductoFavorito) usuarioRepo.obtenerProductoFavoritos(usuario.getCodigo());
            if (proBuscado.isEmpty() || usuBuscado.isEmpty()) {
                throw new Exception("El Producto ó Usuario no existe");
            }
            ProductoFavorito pf = new ProductoFavorito(producto, usuario);
            pf.setProducto(producto);
            pf.setUsuario(usuario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {
        Optional<Producto> buscado = productoRepo.findById(producto.getCodigo());
        if (buscado.isEmpty()){
            throw new Exception("El código del producto no existe");
        }

    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {
        return productoRepo.buscarProductoXNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductosXUsuario(Integer codigoUsuario) throws Exception {
        return productoRepo.obtenerProductosXUsuario(codigoUsuario);
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
    public Float obtenerCalificacionPromedioProducto(Integer codigo) {
        return productoRepo.obtenerPromedioCalificaciones(codigo);
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

    @Override
    public List<Producto> obtenerProductoXPrecio(Integer precio, Integer precio2){
        return productoRepo.listarProductoPrecio(precio, precio2);
    }

    @Override
    public List<Producto> obtenerProductoXPrecio2(Integer precio) {
        return productoRepo.listarProductoPrecio2(precio);
    }

    @Override
    public List<Producto> obtenerProductoXUbicacion(String ciudad) {
        return productoRepo.listarProductoXCiudad(ciudad);
    }


}
