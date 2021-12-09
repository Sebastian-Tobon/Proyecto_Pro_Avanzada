package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
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
import java.util.List;

@Component
@ViewScoped
public class CategoriaBean implements Serializable {

    @Value("#{param['categoria']}")
    private String categoria;

    @Setter @Getter
    private List<Categoria> categorias;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private List<Producto> productos;


    @PostConstruct
    public void inicializar(){
        if (categoria != null) {

            this.productos = productoServicio.listarProductosXNameCategoria(categoria);
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No se encontro Categoria");
        FacesContext.getCurrentInstance().addMessage("msgs", fm);
    }



}
