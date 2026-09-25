package service;

import java.math.BigDecimal; //Para precios
import model.Producto;
import repository.ProductoRepository;
import java.util.HashMap;


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

    public void eliminarProducto(Integer idProducto){
        Integer idProd = productoRepository.retornarIdProducto(idProducto);
        if(idProd == null){
            throw new IllegalArgumentException("El código del producto no existe.");
        }
        productoRepository.eliminarProducto(idProducto);
    }

    public HashMap<Integer, Producto> listarProductos(){

        HashMap<Integer, Producto> productos = productoRepository.listarProductos();
        if (productos.isEmpty()) {
            throw new IllegalArgumentException("No hay productos registrados.");
        }
        return productos;
    }

    public Producto productoConMayorStock() {
        Producto productoMayorStock = productoRepository.productoMayorStock();
        if (productoMayorStock == null) {
            throw new IllegalArgumentException("No hay productos registrados.");
        }

        return productoMayorStock;
    }

    public Producto productoConMenorStock() {
        Producto productoMenorStock = productoRepository.productoMenorStock();
        if (productoMenorStock == null) {
            throw new IllegalArgumentException("No hay productos registrados.");
        }

        return productoMenorStock;
    }

    public BigDecimal valorTotalInventario() {
        BigDecimal valorTotal = productoRepository.valorTotalInventario();
        if (valorTotal == null) {
            throw new IllegalArgumentException("No hay productos registrados.");
        }
        return valorTotal;
    }

    public HashMap<Integer, Producto> productosAgotados() {
        HashMap<Integer, Producto> productosAgotados = productoRepository.productosAgotados();
        if (productosAgotados.isEmpty()) {
            throw new IllegalArgumentException("No hay productos agotados.");
        }
        return productosAgotados;
    }

    public HashMap<Integer, Producto> productosConMenosDeCincoUnidades() {
        
        HashMap<Integer, Producto> productos = productoRepository.productosConMenosDeCincoUnidades();
        if (productos.isEmpty()) {
            throw new IllegalArgumentException("No hay productos con menos de 5 unidades.");
        }
        return productos;
    }
}