package service;

import java.math.BigDecimal;
import model.Producto;
import repository.ProductoRepository;


public class ProductoService {
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

        public void registrarProducto(Producto producto){
        Integer idProducto = productoRepository.retornarIdProducto(producto.getIdProducto());
        if(idProducto != null){
            throw new IllegalArgumentException("El código del producto ya existe.");
        }
        if(producto.getCantidad() < 0){
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if(producto.getPrecio().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        productoRepository.agregarProducto(producto);
    }
}
