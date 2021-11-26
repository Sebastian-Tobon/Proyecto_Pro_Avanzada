package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.io.Serializable;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Setter @Getter
    private boolean autenticado;

    @Setter @Getter
    private String email, password;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Setter @Getter
    private Usuario usuarioSesion;

    public String iniciarSesion() {
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                usuarioServicio.iniciarSesion(email, password);
                autenticado = true;
                return "index?faces-redirect=true";
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}
