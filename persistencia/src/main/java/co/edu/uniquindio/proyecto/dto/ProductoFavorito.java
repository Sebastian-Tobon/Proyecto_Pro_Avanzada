package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoFavorito {

    @EqualsAndHashCode.Include
    private Producto producto;
    private Usuario usuario;

}
