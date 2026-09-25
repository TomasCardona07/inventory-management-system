package service;
import model.Proveedor;
import repository.ProveedorRepository;
import java.util.HashMap;

public class ProveedorService {
    private ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public void registrarProveedor(Proveedor proveedor) {
        Integer idProveedor = proveedorRepository.retornarIdProveedor(proveedor.getIdentificador());
        if (idProveedor != null) {
            throw new IllegalArgumentException("El identificador del proveedor ya existe.");
        }
        proveedorRepository.agregarProveedor(proveedor);
    }

    public void eliminarProveedor(Integer idProveedor) {

        Integer idProv = proveedorRepository.retornarIdProveedor(idProveedor);
        if (idProv == null) {
            throw new IllegalArgumentException("El identificador del proveedor no existe.");
        }
        proveedorRepository.eliminarProveedor(idProveedor);
    }

    public HashMap<Integer, Proveedor> listarProveedores() {
        HashMap<Integer, Proveedor> proveedores = proveedorRepository.listarProveedores();
        if (proveedores.isEmpty()) {  //se retornó el mapa, asi que solo se evalua si es vacio o no
            throw new IllegalArgumentException("No hay proveedores registrados.");
        }
        return proveedores;
    }
}
