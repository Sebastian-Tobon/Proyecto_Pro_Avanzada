package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface CiudadServicio {

    Ciudad agregarCiudad(Ciudad c) throws Exception;

    Ciudad actualizarCiudad(Ciudad p) throws Exception;

    void eliminarCiudad(Integer codigo) throws Exception;

    Ciudad obtenerCiudadXnombre(String nombre) throws Exception;

    Ciudad obtenerCiudadXCodigo(Integer codigo) throws Exception;

    List<Usuario> listaUsuarios(String nombre) throws Exception;



}
