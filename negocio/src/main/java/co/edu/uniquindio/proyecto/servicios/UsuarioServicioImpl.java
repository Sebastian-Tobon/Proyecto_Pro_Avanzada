package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements  UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if (buscado.isPresent()){
            throw  new Exception("El Código del Usuario ya Existe");
        }

        buscado = buscarPorEmail(u.getEmail());
        if (buscado.isPresent()){
            throw  new Exception("El Email del Usuario ya Existe");
        }

        buscado = usuarioRepo.findByUsername(u.getUsername());
        if (buscado.isPresent()){
            throw  new Exception("El Username del Usuario ya Existe");
        }

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        u.setPassword(passwordEncryptor.encryptPassword(u.getPassword()));

        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if (buscado.isEmpty()){
            throw  new Exception("El Usuario no Existe");
        }

        return usuarioRepo.save(u);
    }

    private Usuario buscarUsuario(Usuario u) throws Exception {
        return usuarioRepo.save(u);
    }

    private Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }
    private Optional<Usuario> buscarPorCodigo(Integer codigo) {
        return usuarioRepo.findById(codigo);
    }

    @Override
    public void eliminarUsuario(Integer codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw  new Exception("El Código del Usuario no Existe");
        }
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }


    @Override
    public List<Producto> listaFavoritos(Integer codigo) throws  Exception{
        Optional<Usuario> buscado = buscarPorCodigo(codigo);
        if (buscado.isEmpty()){
            throw  new Exception("El Email  no Existe");
        }
        return usuarioRepo.obtenerProductoFavoritos(codigo);
    }

    @Override
    public Usuario obtenerUsuarioXCodigo(Integer codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw  new Exception("El Usuario no Existe");
        }
        return buscado.get();
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        Usuario usuarioGuardado = usuarioRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de Autenticación son Incorrectos"));
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password, usuarioGuardado.getPassword())){
            return  usuarioGuardado;
        }else {
            throw new Exception("La Contraseña es Incorrecta");
        }
    }

}
