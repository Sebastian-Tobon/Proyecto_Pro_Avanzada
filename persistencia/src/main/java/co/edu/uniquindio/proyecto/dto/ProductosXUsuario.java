package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ProductosXUsuario {

    private  Integer cedula;
    private  String email;
    private  Long registros;
}
