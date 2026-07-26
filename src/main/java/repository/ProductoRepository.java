package repository;
import java.util.*;
import model.*;
//JACKSON Y EXCEPCIONES
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductoRepository {

    // ========== CREAR COLECCIONES DE PRODUCTOS ==========
    private final Map<String, Producto> mapaProductos = new HashMap<>();

    // ========= RETORNAR PRODUCTO ===========
    public Producto retornarProducto(String codigo){
        return mapaProductos.get(codigo);
    }

    // ========== AGREGAR NUEVO PRODUCTO =========
    public void agregarProducto(String codigo, Producto producto){
        this.mapaProductos.put(codigo, producto);
    }

    // ============ ELIMINAR PRODUCTO ============
    public void eliminarProducto(String codigo){
        mapaProductos.remove(codigo);
    }


    // ========== MOSTRAR PRODUCTOS ==========
    public Map<String,Producto> getProductos(){
        return this.mapaProductos;
    }


    // ========== VERIFICAR SI EL MAPA ESTA VACIO ==========
    public boolean mapaVacio(){
        return this.mapaProductos.isEmpty();
    }

    //=========== GUARDAR MAPA EN JSON ============
    public void guardarJson(){
        File productoJson = new File("data/producto.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(productoJson,mapaProductos);
        } catch (IOException e) {
            System.err.println("Error al guardar productos en json");
        }
    }

    //=========== CARGAR JSON ============
    public void cargarJson(){
        File cargarProductos = new File("data/producto.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String,Producto> datos = mapper.readValue(cargarProductos,
                new TypeReference<Map<String,Producto>>() {}
            );
            mapaProductos.clear();
            mapaProductos.putAll(datos);
        } catch (IOException e) {
            System.out.println("Error en cargar Productos");
        }
    }
}