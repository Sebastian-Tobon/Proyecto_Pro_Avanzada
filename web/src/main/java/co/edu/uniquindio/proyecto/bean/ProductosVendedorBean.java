package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class ProductosVendedorBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar() throws Exception {
        this.productos = productoServicio.listarProductosXUsuario(usuarioSesion.getCodigo());
    }
}
