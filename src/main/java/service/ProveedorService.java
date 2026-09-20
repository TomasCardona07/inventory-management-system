package service;
import model.Proveedor;
import repository.ProveedorRepository;

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
}
