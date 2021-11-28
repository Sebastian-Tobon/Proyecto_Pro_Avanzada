package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    //@Getter @Setter
    //private List<Integer> calificacionpromedio;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public  void  inicializador(){
        nuevoComentario = new Comentario();
        if (codigoProducto != null && !codigoProducto.isEmpty()) {
            Integer codigo = Integer.parseInt(codigoProducto);
            producto = productoServicio.obtenerProducto(codigo);
            this.comentarios = producto.getListaComentarios();
           // Float calificacion = productoServicio.obtenerPromedioProducto(codigo);  //implementar
           // if (calificaion != null){
            //    this.calificacionPromedio = calificacion.inValue();            //implementar
            //}
        }
    }

    public void crearComentario(){

        try {
            if (usuarioSesion != null) {
                nuevoComentario.setProducto(producto);
                nuevoComentario.setUsuario(usuarioSesion);
                productoServicio.comentarProducto(nuevoComentario);
                this.comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-pregunta", fm);
        }
    }
}
