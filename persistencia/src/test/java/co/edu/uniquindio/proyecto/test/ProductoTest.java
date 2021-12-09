package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.dto.ProductosXUsuario;
import co.edu.uniquindio.proyecto.dto.UsuarioYProducto;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    //Registrar Producto
    @Test
    @Sql("classpath:dbInserts.sql")
    public void registrarProductoTest(){

        //Traigo el Usuario (2001) "Mario Fuentes"
        Usuario usuario = usuarioRepo.findById(2001).orElse(null);

        //Traigo la ciudad (2) "Pereira"
        Ciudad ciudad = ciudadRepo.findById(2).orElse(null);

        //Creo un Producto
        //Producto producto = new Producto(5, "Teclado Gamer", 10, "61 teclas multicolor RGB iluminado LED retroiluminado programable para PC/Mac Gamer",
                //190000, LocalDateTime.of(2022,02,15,23,59), 0.2f, usuario, ciudad);
        //Guardo el Producto
        //Producto usuarioGuardado = productoRepo.save(producto);

        //System.out.println(usuarioGuardado);
        //Verificaión
        //Assertions.assertNotNull(usuarioGuardado);
    }

    //Actualizar Producto
    @Test
    @Sql("classpath:dbInserts.sql")
    public void actualizarProductoTest(){

        Producto guardado = productoRepo.findById(1).orElse(null);
        //modifico el Producto
        guardado.setNombre("Morral de 10Lts");

        //guardo el Producto
        productoRepo.save(guardado);

        Producto productoBuscado = productoRepo.findById(1).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals("Morral de 10Lts", productoBuscado.getNombre());
        System.out.println("--Producto--: "+productoBuscado);

    }

    //Eliminar Producto
    @Test
    @Sql("classpath:dbInserts.sql")
    public void eliminarProductoTest(){

        //Elimino el Producto "3"
        productoRepo.deleteById(3);

        //Busco el Producto eliminado
        Producto productoBuscado = productoRepo.findById(3).orElse(null);

        Assertions.assertNull(productoBuscado);
        System.out.println("Producto Eliminado");

    }

    //Listar Producto
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarProductoTest(){
        //Listo los Productos
        List<Producto> productos = productoRepo.findAll();

        //Imprimir la lista de Productos
        productos.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void obtenerNombreVerdedorTest(){

        String nombre = productoRepo.obtenerNombreVendedor(1);
        Assertions.assertEquals("Maria Cardenas",nombre);
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void obtnUsuarioProdsFavsTest() {
  //      List<Producto> listaProductosFavoritos = usuarioRepo.obtenerProductoFavoritos("maria@email.com");
    //    Assertions.assertEquals(2, listaProductosFavoritos.size());
//        System.out.println(listaProductosFavoritos);
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarUsuariosProductosTest() {
        List<UsuarioYProducto> respuesta = usuarioRepo.listarUsuariosYProductos();
        respuesta.forEach(System.out::println);
        Assertions.assertEquals(3,respuesta.size());
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarProductosYComentariosTest() {
        List<Object[]> respuesta = productoRepo.listarProductosYComentarios();
        respuesta.forEach(objeto -> System.out.println(objeto[0]+"---"+objeto[1]));
        Assertions.assertEquals(3,respuesta.size());
    }
    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarUsuariosComentariosTest() {
        List<Usuario> usuarios = productoRepo.listarUsuariosComentarios(1);
        usuarios.forEach(System.out::println);
        Assertions.assertEquals(1,usuarios.size());
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarProductosValidosTest() {
        List<ProductoValido> productos = productoRepo.listarProductosValidos(LocalDateTime.now());
        productos.forEach(System.out::println);
        Assertions.assertEquals(3,productos.size());
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarProductosXCategoriaTest() {
        List<Object[]> respuesta = productoRepo.obtenerTotalProductosXCategoria();
        respuesta.forEach(r -> System.out.println(r[0]+","+r[1]));
        Assertions.assertEquals(4,respuesta.size());
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void listarProductosSinComentariosTest() {
        List<Producto> productos = productoRepo.obtenerProductosSinComentarios();
        productos.forEach(System.out::println);
        Assertions.assertEquals(1,productos.size());
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void obtenerProductoxNombreTest() {
        List<Producto> productos = productoRepo.buscarProductoXNombre("Camping");
        productos.forEach(System.out::println);
        Assertions.assertEquals(2,productos.size());
    }

    @Test
    @Sql("classpath:dbInserts.sql")
    public void obtenerProductosEnVentaTest() {
        List<ProductosXUsuario> productos = productoRepo.obtenerProductosEnVenta();
        productos.forEach(System.out::println);
        Assertions.assertEquals(4,productos.size());
    }
    @Test
    @Sql("classpath:dbInserts.sql")
    public void obtenerProductosRangoPrecioTest() {
        List<Producto> productos = productoRepo.listarProductoPrecio2(1000000);
        productos.forEach(System.out::println);
        Assertions.assertEquals(3,productos.size());
    }
    @Test
    @Sql("classpath:dbInserts.sql")
    public void obtenerProductosXCategoriaTest() {
        //Categoria categoria = categoriaRepo.findById(2).orElse(null);
       // Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        //String nombre = ciudad.getNombre();
        //List<Producto> productos = productoRepo.listarCategoriaProducto();
       //productos.forEach(System.out::println);
        //Assertions.assertEquals(4,productos.size());
    }
}
