package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

public interface AdminServicio {

    Administrador iniciarSesion(String email, String password) throws Exception;
}
