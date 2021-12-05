package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CompraProductoUsuario {

    private DetalleCompra detalleCompra;
    private Compra compra;
}
