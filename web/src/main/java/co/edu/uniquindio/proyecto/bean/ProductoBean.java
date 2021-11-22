package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@Component
@ViewScoped
public class ProductoBean {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;

    @PostConstruct
    public void inicializar(){
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
    }

    public void publicarProducto(){
        try {
            if(!imagenes.isEmpty()) {
                //Quemo el usuario Vendedor al producto (Eliminar cuando se implemete las sesiones)
                Usuario usuario = usuarioServicio.obtenerUsuarioXCodigo(123);
                producto.setVendedor(usuario);
                producto.setListaImagenes(imagenes);
                productoServicio.publicarProducto(producto);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto Creado Satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Es necesario subir al menos una Imagen para el Producto");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
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
