package co.edu.uniquindo.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarUsuariosTest(){
        Usuario u = new Usuario(3, "pepito", "pepe@email.com", "456as", "elpepe",null);
        try {
            Usuario respuesta = usuarioServicio.registrarUsuario(u);
            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarUsuariosTest(){
        try {
            usuarioServicio.eliminarUsuario(3);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listarUsuariosTest(){
        List<Usuario> lista = usuarioServicio.listarUsuarios();
        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarUsuariosTest(){
        try {
            Usuario u = usuarioServicio.obtenerUsuarioXCodigo(3);
            u.setPassword("as$%/d_45");
            usuarioServicio.actualizarUsuario(u);
            Usuario modificado = usuarioServicio.obtenerUsuarioXCodigo(3);
            Assertions.assertEquals("as$%/d_45", modificado.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Todos los requerimientos del proyecto
}
