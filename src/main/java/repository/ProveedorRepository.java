package repository;
import java.util.*;
import model.Proveedor;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProveedorRepository {
    // ========== CREAR COLECCIONES DE PROVEEDORES ==========
    private final Map<String, Proveedor> mapaProveedores = new HashMap<>();


    // ========== RETORNAR PROVEEDOR ===========
    public Proveedor retornarProveedor(String identificador){
        Proveedor proveedor = mapaProveedores.get(identificador);
        return proveedor;
    }

    // ========= AGREGAR NUEVO PROVEEDOR =========
    public void agregarProveedor(String identificador, Proveedor proveedor){
        mapaProveedores.put(identificador, proveedor);
    }


    // ============= ELIMINAR PROVEEDOR =============
    public void eliminarProveedor(String identificador){
        mapaProveedores.remove(identificador);
    }

    // ========== MOSTRAR PROVEEDORES ==========
    public Map<String,Proveedor> getProveedores(){
        return this.mapaProveedores;
    }

    // ========== VERIFICAR SI EL MAPA ESTA VACIO ==========
    public boolean mapaVacio(){
        return this.mapaProveedores.isEmpty();
    }


    // ======== AGREGAR A JSON ========
    public void guardarJson(){
        File proveedor = new File("data/proveedor.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(proveedor, mapaProveedores);
        } catch (IOException e) {
            System.err.println("No se pudo agregar proveedores");
        }
    }

    // ======== CARGAR JSON ========
    public void cargarJson(){
        File proveedor = new File("data/proveedor.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String,Proveedor> proveedores = mapper
                .readValue(proveedor, new TypeReference <Map<String,Proveedor>>(){});
            mapaProveedores.clear();
            mapaProveedores.putAll(proveedores);
        } catch (IOException e) {
            System.err.println("no se pudo cargar los proveedores");
        }
    }
}
