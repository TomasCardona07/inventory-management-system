package com.inventory.management.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.inventory.management.dto.MovimientoDtoRequest;
import com.inventory.management.dto.MovimientoDtoResponse;
import com.inventory.management.exception.movimiento.MovimientoNoEncontradoException;
import com.inventory.management.exception.movimiento.StockInsuficienteException;
import com.inventory.management.exception.producto.ProductoDesactivadoException;
import com.inventory.management.exception.proveedor.ProveedorDesactivadoException;
import com.inventory.management.exception.proveedor.ProveedorObligatorioException;
import com.inventory.management.model.Movimiento;
import com.inventory.management.model.Producto;
import com.inventory.management.model.Proveedor;
import com.inventory.management.model.TipoMovimiento;
import com.inventory.management.repository.MovimientoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class MovimientoService {

    private final ProductoService productoService;
    private final ProveedorService proveedorService;
    private final MovimientoRepository movimientoRepository;

    public MovimientoService(ProductoService productoService, ProveedorService proveedorService, MovimientoRepository movimientoRepository){
        this.productoService = productoService;
        this.proveedorService = proveedorService;
        this.movimientoRepository = movimientoRepository;
    }

    // Entidad a DTO
    private MovimientoDtoResponse convertirADTO(Movimiento movimiento){
        return new MovimientoDtoResponse(
            movimiento.getId(),
            movimiento.getProducto().getId(),
            movimiento.getProveedor().getId(),
            movimiento.getCantidad(),
            movimiento.getTipo(),
            movimiento.getFecha()
        );
    }


    //Registrar entrada
    @Transactional 
    public MovimientoDtoResponse registrarEntrada(MovimientoDtoRequest request){
        if (request.idProveedor() == null) {
            throw new ProveedorObligatorioException("El proveedor es obligatorio");
        }

        Producto producto = productoService.obtenerProducto(request.idProducto());
        if (Boolean.FALSE.equals(producto.getEstado())) {
            throw new ProductoDesactivadoException("El producto se encuentra desactivado");
        }

        Proveedor proveedor = proveedorService.obtenerProveedorPorId(request.idProveedor());
        if (Boolean.FALSE.equals(proveedor.getEstado())) {
            throw new ProveedorDesactivadoException("El proveedor se encuentra desactivado");
        }

        producto.setCantidad(producto.getCantidad() + request.cantidad());
        
        Movimiento nuevoMovimiento = new Movimiento();
        nuevoMovimiento.setProducto(producto);
        nuevoMovimiento.setProveedor(proveedor);
        nuevoMovimiento.setCantidad(request.cantidad());
        nuevoMovimiento.setTipo(TipoMovimiento.ENTRADA);

        Movimiento movimientoRegistrado = movimientoRepository.save(nuevoMovimiento);

        return convertirADTO(movimientoRegistrado);
    }

    //Registrar Salida
    @Transactional 
    public MovimientoDtoResponse registrarSalida(MovimientoDtoRequest request){
        Producto producto = productoService.obtenerProducto(request.idProducto());

        if (request.cantidad() > producto.getCantidad()) {
            throw new StockInsuficienteException("La cantidad supera al stock actual");
        }

        producto.setCantidad(producto.getCantidad() - request.cantidad());
        Movimiento nuevoMovimiento = new Movimiento();
        nuevoMovimiento.setProducto(producto);
        nuevoMovimiento.setProveedor(null);
        nuevoMovimiento.setCantidad(request.cantidad());
        nuevoMovimiento.setTipo(TipoMovimiento.SALIDA);

        Movimiento movimientoRegistrado = movimientoRepository.save(nuevoMovimiento);

        return convertirADTO(movimientoRegistrado);
    }

    //Mostrar historial
    public List<MovimientoDtoResponse> mostrarHistorial(){
        return movimientoRepository.findAllByOrderByFechaDesc()
            .stream()
            .map(movimiento -> convertirADTO(movimiento))
            .toList();
    }

    //Buscar movimiento
    public MovimientoDtoResponse buscarMovimientoPorId(Long id){
        Movimiento movimiento = obtenerMovimiento(id);
        return convertirADTO(movimiento);
    }

    //Obtener movimiento por id
    private Movimiento obtenerMovimiento(Long id){
        return movimientoRepository.findById(id)
            .orElseThrow(() -> new MovimientoNoEncontradoException("Movimiento no encontrado"));
    }

    //Eliminar movimiento por id
    public void eliminarMovimiento(Long id){ //No recomiendo eliminar historial
        Movimiento movimiento = obtenerMovimiento(id);
        movimientoRepository.delete(movimiento);
    }
}
