package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Setter @Getter
    private boolean autenticado;

    @Setter @Getter
    private String email, password;

    @Setter @Getter
    private ArrayList<ProductoCarrito> productoCarrito;

    @Setter @Getter
    private  Float subtotal;

    @Setter @Getter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar(){
        this.subtotal = 0F;
        this.productoCarrito = new ArrayList<>();
    }

    public String iniciarSesion() {
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                usuarioSesion = usuarioServicio.iniciarSesion(email, password);
                autenticado = true;
                return "/index?faces-redirect=true";
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }
        return null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarAlCarrito(Integer id, Float precio, String nombre, String imagen){
        ProductoCarrito pc = new ProductoCarrito(id, nombre, imagen, precio, 1);
        if (!productoCarrito.contains(pc)) {
            productoCarrito.add(pc);
            subtotal += precio;

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto Agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }
    }

    public void eliminarDelCarrito(int indice){
        subtotal -= productoCarrito.get(indice).getPrecio();
        productoCarrito.remove(indice);

    }

    public void actualizarSubtotal(){
        subtotal = 0F;
        for (ProductoCarrito p:productoCarrito) {
            subtotal += p.getPrecio()*p.getUnidades();
        }
    }

    public void comprar() {
        if (usuarioSesion != null && !productoCarrito.isEmpty()){
            try {   //Verificar que el producto tenga las cantidades que se van a comprar
                productoServicio.comprarProductos(usuarioSesion, productoCarrito, "PSE");       //Modificar desde el front
                productoCarrito.clear();
                subtotal = 0F;
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra Realizada Satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }
    }
}
