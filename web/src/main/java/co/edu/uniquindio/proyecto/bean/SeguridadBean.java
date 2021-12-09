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
import javax.validation.constraints.NotBlank;
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
    private Usuario usuarioSesion;

    @Getter @Setter
    private Usuario usuarioAux;

    @Setter @Getter
    private Administrador adminSesion;

    @Getter @Setter
    @NotBlank
    private String emailR;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CompraServicio compraServicio;

    @PostConstruct
    public void inicializar(){
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
                subtotal = 0F;
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra Realizada Satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
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

   //recuperar contraseña
   public void sendMail()
   {
       String subject = "Recuperacion de contraseña";
       String url = "http://localhost:8080/recuperarContrasena.xhtml";
       String message = "Un saludo por parte de unishop!" + "\n" + " Para recuperar su contraseña, de click en el siguiente enlace: " + "\n" + url;

       emailSenderService.sendMail("sebas.tobon1097@gmail.com", usuarioAux.getEmail(),subject,message);
   }

    //Método para cambiar clave

    public void cambiarPassword(){

        try {
            if (!password.isEmpty()){

                usuarioServicio.cambiarPassword(email,password);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La contraseña se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No se pudo actualizar la contraseña");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

        }catch (Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }

    }

    public String buscarPorEmail(){

        try {
            usuarioAux = usuarioServicio.obtenerPersonaEmail(emailR);

            if(usuarioAux!=null){

                sendMail();
            }else{

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "El email que ingreso no se encuentra registrado");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

            return "/index?faces-redirect=true";

        }catch (Exception e){

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
        return null;
    }

}
