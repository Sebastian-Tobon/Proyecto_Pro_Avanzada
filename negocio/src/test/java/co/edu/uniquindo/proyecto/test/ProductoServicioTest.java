package co.edu.uniquindo.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void obtenerProductoTest(){
        try {
            Usuario vendedor = usuarioServicio.obtenerUsuarioXCodigo(3);
            LocalDateTime ldt = LocalDateTime.of(2021, 11, 30, 20, 10);
            Producto producto = new Producto("Televisor LG 55'", 15, "Increible Televisor LG 55'", "publicacion ok", 2500000, ldt, 0.1F, vendedor);
            Producto publicado = productoServicio.publicarProducto(producto);
            Assertions.assertNotNull(publicado);
        }catch (Exception e){
            Assertions.assertTrue(false, e.getMessage());
        }
    }

}
