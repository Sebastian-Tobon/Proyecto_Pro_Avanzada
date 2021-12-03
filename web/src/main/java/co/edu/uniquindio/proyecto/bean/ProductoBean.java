package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean {

    @Getter @Setter
    private Producto producto;

    @Setter @Getter
    private List<Categoria> categorias;

    @Setter @Getter
    private List<Ciudad> ciudades;

    private final ProductoServicio productoServicio;

    private final UsuarioServicio usuarioServicio;

    private final CiudadServicio ciudadServicio;

    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    public ProductoBean(ProductoServicio productoServicio, UsuarioServicio usuarioServicio, CiudadServicio ciudadServicio) {
        this.productoServicio = productoServicio;
        this.usuarioServicio = usuarioServicio;
        this.ciudadServicio = ciudadServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        categorias = productoServicio.listarCategorias();
        ciudades = ciudadServicio.listarCiudades();
    }

    public void publicarProducto(){
        try {
            if (usuarioSesion != null) {
                if (!imagenes.isEmpty()) {
                    producto.setVendedor(usuarioSesion);
                    producto.setListaImagenes(imagenes);
                    producto.setFecha_limite(LocalDateTime.now().plusMonths(2));
                    productoServicio.publicarProducto(producto);
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto Creado Satisfactoriamente");
                    FacesContext.getCurrentInstance().addMessage("mensaje-bean", msg);
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Es necesario subir al menos una Imagen para el Producto");
                    FacesContext.getCurrentInstance().addMessage("mensaje-bean", msg);
                }
            }
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje-bean",msg);
        }
    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if (nombreImagen!=null){
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile imagen){
        try {
            File file = new File(urlUploads + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
