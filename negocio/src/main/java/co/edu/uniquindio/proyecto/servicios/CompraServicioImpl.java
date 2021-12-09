package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
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

    @Override
    public Long totalPrecioCompra(Integer codigo) {
        return compraRepo.calcularTotalCompras(codigo);
    }

    @Override
    public List<Producto> listaProductoComprado2(Integer codigo) throws Exception {
        return compraRepo.obtenerComprasXUsuario(codigo);
    }

}
