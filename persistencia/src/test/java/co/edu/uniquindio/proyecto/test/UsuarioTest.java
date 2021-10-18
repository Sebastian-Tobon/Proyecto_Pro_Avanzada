package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.GeneroPersona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:usuarios.sql")
    public void registrarTest(){

        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);

        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("casa", "1717171");
        telefonos.put("celular", "9828298");

        Usuario usuario = new Usuario("123","Pepipito",LocalDate.now(),GeneroPersona.MASCULINO, "pepe@email.com", telefonos, ciudad);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        System.out.println(usuarioGuardado);

        Assertions.assertNotNull(usuarioGuardado);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarUsuarioTest(){

        usuarioRepo.deleteById("123");

        Usuario usuarioBuscado = usuarioRepo.findById("123").orElse(null);

        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarUsuarioTest(){

        Usuario guardado = usuarioRepo.findById("124").orElse(null);

        //midifico el usuario
        guardado.setEmail("maria_nuevo@email.com");

        //guardo el usuario
        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById("124").orElse(null);
        //verifico lo modificado
        Assertions.assertEquals("maria_nuevo@email.com", usuarioBuscado.getEmail());
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuarioTest(){
        List<Usuario> usuarios = usuarioRepo.findAll();

        usuarios.forEach(u -> System.out.println(u));
        System.out.println(usuarios);
    }

}
