package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicioImpl implements CiudadServicio{

    private final CiudadRepo ciudadRepo;

    public CiudadServicioImpl(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Override
    public Ciudad agregarCiudad(Ciudad c) throws Exception {
        Optional<Ciudad> buscado = ciudadRepo.findByNombre(c.getNombre());

        if (buscado.isPresent()){
            throw new Exception("El nombre de la ciudad ya Existe");
        }
        return ciudadRepo.save(c);
    }

    @Override
    public Ciudad actualizarCiudad(Ciudad c) throws Exception {
        Optional<Ciudad> buscado = ciudadRepo.findByNombre(c.getNombre());

        if (buscado.isEmpty()){
            throw new Exception("La Ciudad no Existe");
        }
        return ciudadRepo.save(c);
    }

    @Override
    public void eliminarCiudad(Integer codigo) throws Exception {
        Optional<Ciudad> buscado = ciudadRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw  new Exception("El Código de la Ciudad no Existe");
        }
        ciudadRepo.deleteById(codigo);

    }

    @Override
    public Ciudad obtenerCiudadXnombre(String nombre) throws Exception {
        Optional<Ciudad> buscado = ciudadRepo.findByNombre(nombre);
        if (buscado.isEmpty()){
            throw  new Exception("La Ciudad no Existe");
        }
        return buscado.get();
    }

    @Override
    public Ciudad obtenerCiudadXCodigo(Integer codigo) throws Exception {
        return ciudadRepo.findById(codigo).orElseThrow(() -> new Exception("El Id no Corresponde a ninguna Ciudad"));
    }

    private Optional<Ciudad> buscarPorNombre(String nombre) {
        return ciudadRepo.findByNombre(nombre);
    }

    @Override
    public List<Usuario> listarUsuarios(String nombre) throws Exception {
        Optional<Ciudad> buscado = buscarPorNombre(nombre);
        if (buscado.isEmpty()){
            throw  new Exception("El nombre de la Ciudad no Existe");
        }
        return ciudadRepo.listarUsuarios(nombre);
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }
}
