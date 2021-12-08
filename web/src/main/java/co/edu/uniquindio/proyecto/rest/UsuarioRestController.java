package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsu(@PathVariable("id") Integer idUsuario){
        try {
            Usuario usuario = usuarioServicio.obtenerUsuarioXCodigo(idUsuario);
            return  ResponseEntity.status(200).body(usuario);
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> crearUsu(@RequestBody Usuario usuario){
        try {
            usuarioServicio.registrarUsuario(usuario);
            return  ResponseEntity.status(201).body(new Mensaje("El Usuario Se Registro correctamente"));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizar(@RequestBody Usuario usuario) {
        try {
            usuarioServicio.actualizarUsuario(usuario);
            return  ResponseEntity.status(200).body(new Mensaje("El Usuario Se Actualizo correctamente"));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id")Integer id) {
        try {
            usuarioServicio.eliminarUsuario(id);
            return  ResponseEntity.status(200).body(new Mensaje("El Usuario Se Eliminó correctamente"));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/favortios/{email}")
    public ResponseEntity<?> obtenerFavoritos(@PathVariable("id")Integer id){
        try {
            List<Producto> lista =usuarioServicio.listaFavoritos(id);
            return  ResponseEntity.status(200).body(lista);
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

}
