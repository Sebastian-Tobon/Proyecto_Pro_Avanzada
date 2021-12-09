package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.stereotype.Service;

@Service
public class AdminServicioImpl implements AdminServicio{
    private final AdministradorRepo administradorRepo;

    public AdminServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Administrador iniciarSesion(String email, String password) throws Exception {
        Administrador adminEmail = administradorRepo.findByEmailAndPassword(email,password).orElseThrow(() -> new Exception("El administrador no Existe"));
        return adminEmail;
    }
}
