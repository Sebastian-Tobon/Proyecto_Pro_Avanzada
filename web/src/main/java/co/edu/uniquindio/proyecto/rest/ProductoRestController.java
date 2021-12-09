package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;
    //Obtengo todos los productos
    @GetMapping
    public List<Producto> listar(){
        return productoServicio.listarTodosProductos();
    }
    //Obtener un registro de los productos por su ID.
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPro(@PathVariable("id") Integer idProducto){
        try {
            Producto producto = productoServicio.obtenerProductoXCodigo(idProducto);
            return  ResponseEntity.status(200).body(producto);
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
   //Registrar un producto
    @PostMapping
    public ResponseEntity<Mensaje> crearPro(@RequestBody Producto producto){
        try {
            productoServicio.publicarProducto(producto);
            return  ResponseEntity.status(201).body(new Mensaje("El Producto Se Registro correctamente"));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    //Actulizar un producto
    @PutMapping
    public ResponseEntity<Mensaje> actualizarPro(@RequestBody Producto producto) {
        try {
            productoServicio.actualizarProducto(producto);
            return  ResponseEntity.status(200).body(new Mensaje("El Producto Se Actualizo correctamente"));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    //Eliminar un producto
    @DeleteMapping("/codigo/{id}")
    public ResponseEntity<Mensaje> eliminarPro(@PathVariable("id")Integer id) {
        try {
            productoServicio.eliminarProducto(id);
            return  ResponseEntity.status(200).body(new Mensaje("El Producto Se Eliminó correctamente"));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    //listar Productos por categoria
   @GetMapping("/categoria/{cat}")
    public ResponseEntity<?> listarProductoCat(@PathVariable("cat") String categoria){
       // return productoServicio.listarProductos(categoria);
       List<Producto> lista =  productoServicio.listarProductosXNameCategoria(categoria);
       return  ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/precio/{pre1}&{pre2}")
    public ResponseEntity<?> listarProductoPrecio(@PathVariable("pre1") Integer precio1, @PathVariable("pre2") Integer precio2 ){
        List<Producto> lista =productoServicio.obtenerProductoXPrecio(precio1, precio2);
        return  ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/ciudad/{ciu}")
    public ResponseEntity<?> listarProductoCiudad(@PathVariable("ciu") String ciudad){
        List<Producto> lista =productoServicio.obtenerProductoXUbicacion(ciudad);
        return  ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/calificacion/{cali}")
    public ResponseEntity<?> listarProductoCalificacion(@PathVariable("cali") Integer puntaje){
        List<Producto> lista =productoServicio.listaProductoXcalificacion(puntaje);
        return  ResponseEntity.status(200).body(lista);
    }

/*
    @GetMapping("/favortios/{email}")
    public ResponseEntity<?> obtenerFavoritos(@PathVariable("id")Integer id){
        try {
            List<Producto> lista =usuarioServicio.listaFavoritos(id);
            return  ResponseEntity.status(200).body(lista);
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
*/


}