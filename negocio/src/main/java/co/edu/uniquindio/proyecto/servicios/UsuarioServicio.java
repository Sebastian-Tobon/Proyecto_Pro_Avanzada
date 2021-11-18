package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    void eliminarUsuario(Integer codigo) throws Exception;

    List<Usuario> listarUsuarios();

    List<Producto> listaFavoritos(String email) throws Exception;

    Usuario obtenerUsuarioXCodigo(Integer codigo) throws Exception;

}
