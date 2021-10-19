package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private CiudadRepo  ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //Registrar Chat
    @Test
    @Sql("classpath:chats.sql")
    public void registrarChatTest(){

        //Traigo el Usuario (2001) "Mario Fuentes"
        Usuario usuario = usuarioRepo.findById(2001).orElse(null);

        Chat chat = new Chat(128, usuario);
        Chat chatGuardado = chatRepo.save(chat);

        System.out.println(chatGuardado);
        Assertions.assertNotNull(chatGuardado);
    }

    //Actualizar Chat
    @Test
    @Sql("classpath:chats.sql")
    public void actualizarChatTest(){

        Chat guardado = chatRepo.findById(124).orElse(null);
        //modifico el Usuario
        guardado.setCodigo(128);

        //guardo la categoria
        chatRepo.save(guardado);

        Chat chatBuscado = chatRepo.findById(124).orElse(null);
        //verifico lo modificado
        Assertions.assertEquals(128, chatBuscado.getCodigo());
        System.out.println("--Chat--: "+chatBuscado);

    }

    //Eliminar Chat
    @Test
    @Sql("classpath:chats.sql")
    public void eliminarChatTest(){

        //Elimino el chat "125"
        chatRepo.deleteById(125);

        Chat chatBuscado = chatRepo.findById(125).orElse(null);

        Assertions.assertNull(chatBuscado);
        System.out.println("Chat Eliminado");

    }

    //Listar Chats
    @Test
    @Sql("classpath:chats.sql")
    public void listarChatTest(){
        List<Chat> chats = chatRepo.findAll();

        //Imprimir la lista de Chats
        chats.forEach(u -> System.out.println(u));
    }
}
