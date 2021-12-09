package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.*;
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
import java.util.List;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Setter @Getter
    private boolean autenticado;

    @Setter @Getter
    private boolean autenticadoAdmin;

    @Setter @Getter
    private String email, password;

    @Setter @Getter
    private List<Producto> misPro;

    @Setter @Getter
    private ArrayList<ProductoCarrito> productoCarrito;

    @Setter @Getter
    private  Float subtotal;

    @Setter @Getter
    private  Float total;

    @Setter @Getter
    private Usuario usuarioSesion;

    @Setter @Getter
    private Administrador adminSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private CompraServicio compraServicio;

    @PostConstruct
    public void inicializar(){
        this.total = 0F;
        this.subtotal = 0F;
        this.productoCarrito = new ArrayList<>();
    }

    public String iniciarSesion() {
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                usuarioSesion = usuarioServicio.iniciarSesion(email, password);
                if (usuarioSesion.getCodigo() >= 1 && usuarioSesion.getCodigo() <= 99){
                    autenticadoAdmin = true;
                }else {
                    autenticado = true;
                }
                return "/index?faces-redirect=true";
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta Inicio Sesión", e.getMessage());
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
                total = subtotal;
                subtotal = 0F;
                triggerMail();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra Realizada Satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }
    }

    public void triggerMail() throws Exception {

        String mensaje = "<h1>UNISHOP</h1>";

        mensaje += "<h2>Hola, " + usuarioSesion.getNombre() + "</h2>"
                + "\n\nTu pedido ha sido Confirmado.\n"
                + "\n<h4>TUS COMPRAS</h4>"
                + "<P>" + misProductos() + "</P>"
                + "<h3>Total</h3> "
                + "<P>" + total + "</P>"
                + "<h3>Gracias por Elegirnos</h3> "
                + "</h2></br></br>Atentamente, "
                + "<h3> UNISHOP</h3>";
        try {
            emailSenderService.sendSimpleEmail("sebas.tobon1097@gmail.com", mensaje,
                    "Unishop");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public String misProductos(){
        Integer usuarioCodigo = usuarioSesion.getCodigo();
        try {
            misPro = compraServicio.listaProductoComprado2(usuarioCodigo);
            for (Producto pro: misPro){
                return pro.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
   }


}
