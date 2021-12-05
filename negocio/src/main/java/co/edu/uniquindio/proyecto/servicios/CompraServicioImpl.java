package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicioImpl implements CompraServicio{

    private final CompraRepo compraRepo;

    public CompraServicioImpl(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    @Override
    public List<DetalleCompra> listaProductoComprado(Integer codigo) throws Exception {
        return compraRepo.obtenerListaComprasXUsuario(codigo);
    }


}
