package com.inventory.management.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.inventory.management.dto.ProveedorDtoRequest;
import com.inventory.management.dto.ProveedorDtoResponse;
import com.inventory.management.exception.ProveedorNoEncontradoException;
import com.inventory.management.model.Proveedor;
import com.inventory.management.repository.ProveedorRepository;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }

    //convertir proveedor a DTO
    private ProveedorDtoResponse convertirADTO(Proveedor proveedor){
        return new ProveedorDtoResponse(
            proveedor.getId(),
            proveedor.getNombre(),
            proveedor.getTelefono()
        );
    }

    //Registrar proveedor
    public ProveedorDtoResponse registrarProveedor(ProveedorDtoRequest request){
        Proveedor nuevoProveedor = new Proveedor();
        nuevoProveedor.setNombre(request.nombre());
        nuevoProveedor.setTelefono(request.telefono());
        Proveedor proveedorRegistrado = proveedorRepository.save(nuevoProveedor);
        return convertirADTO(proveedorRegistrado);
    }

    //listar proveedores
    public List<ProveedorDtoResponse> mostrarProveedores(){
        return proveedorRepository.findAll()
            .stream()
            .map(proveedor -> convertirADTO(proveedor))
            .toList();
    }

    //actualizar proveedor
    @Transactional 
    public ProveedorDtoResponse actualizarProveedor(Long id,ProveedorDtoRequest request){
        Proveedor proveedor = obtenerProveedorPorId(id);
        proveedor.setNombre(request.nombre());
        proveedor.setTelefono(request.telefono());
        return convertirADTO(proveedor);
    }

    //eliminar proveedor
    public void eliminarProveedor(Long id){
        Proveedor proveedor = obtenerProveedorPorId(id);
        proveedorRepository.delete(proveedor);
    }

    //buscar proveedor por id
    public ProveedorDtoResponse buscarProveedorPorId(Long id){
        Proveedor proveedor = obtenerProveedorPorId(id);
        return convertirADTO(proveedor);
    }

    //Obtener proveedor por id
    public Proveedor obtenerProveedorPorId(Long id){
        Proveedor proveedor = proveedorRepository.findById(id)
            .orElseThrow(() -> new ProveedorNoEncontradoException("Proveedor no encontrado"));
        return proveedor;
    }
}
