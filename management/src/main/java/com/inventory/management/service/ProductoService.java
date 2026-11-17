package com.inventory.management.service;

import org.springframework.stereotype.Service;
import com.inventory.management.dto.ProductoDtoRequest;
import com.inventory.management.dto.ProductoDtoResponse;
import com.inventory.management.model.Categoria;
import com.inventory.management.model.Producto;
import com.inventory.management.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service 
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaService categoriaService;

    public ProductoService(ProductoRepository productoRepository, CategoriaService categoriaService){
        this.productoRepository = productoRepository;
        this.categoriaService = categoriaService;
    }


    //convertir producto a DTO
    private ProductoDtoResponse convertirADTO(Producto producto){
        return new ProductoDtoResponse(
        producto.getId(),
        producto.getNombre(),
        producto.getCategoria().getId(),
        producto.getCantidad(),
        producto.getPrecio()
        );
    }

    //registrar Producto
    public ProductoDtoResponse registrarProducto(ProductoDtoRequest request){

        Categoria categoria = categoriaService.obtenerCategoriaPorId(request.idCategoria());
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(request.nombre());
        nuevoProducto.setCantidad(request.cantidad());
        nuevoProducto.setPrecio(request.precio());
        nuevoProducto.setCategoria(categoria);

        Producto productoRegistrado = productoRepository.save(nuevoProducto);
        return convertirADTO(productoRegistrado);
    }


    //buscar producto por id
    public ProductoDtoResponse buscarProductoPorId(Long id){
        Producto producto = obtenerProducto(id);
        
        return convertirADTO(producto);
    }

    //Obtener entidad producto
    public Producto obtenerProducto(Long id){
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }


    //actualizar producto
    @Transactional
    public ProductoDtoResponse actualizarProducto(Long id, ProductoDtoRequest request){

        Producto producto = productoRepository.findById(id) //No se reutiliza la funcion buscarProductoPorId porque esa retorna un DTO y aqui se requiere la entidad
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Categoria categoria = categoriaService.obtenerCategoriaPorId(request.idCategoria());
        producto.setNombre(request.nombre());
        producto.setCantidad(request.cantidad());
        producto.setCategoria(categoria);
        producto.setPrecio(request.precio());
        
        return convertirADTO(producto);
    }

    //Eliminar producto
    public void eliminarProducto(Long id){
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoRepository.delete(producto);
    }
}
