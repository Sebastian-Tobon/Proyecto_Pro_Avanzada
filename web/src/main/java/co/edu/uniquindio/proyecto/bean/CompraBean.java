package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
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
public class CompraBean implements Serializable {

    @Autowired
    private CompraServicio compraServicio;

    @Getter @Setter
    private List<DetalleCompra> productosComprados;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar() throws Exception {
        this.productosComprados = compraServicio.listaProductoComprado(usuarioSesion.getCodigo());
    }
}
