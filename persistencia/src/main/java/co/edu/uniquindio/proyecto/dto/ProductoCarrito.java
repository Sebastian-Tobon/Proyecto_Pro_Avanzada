package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ProductoCarrito {

    @EqualsAndHashCode.Include
    private Integer id;
    private String nombre, imagen;
    private Float precio;
    private Integer unidades;
}
