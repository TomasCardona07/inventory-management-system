package com.inventory.management.service;

import org.springframework.stereotype.Service;

import com.inventory.management.dto.ProductoDtoRequest;
import com.inventory.management.dto.ProductoDtoResponse;
import com.inventory.management.model.Categoria;
import com.inventory.management.model.Producto;
import com.inventory.management.repository.ProductoRepository;

@Service 
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaService categoriaService;

    public ProductoService(ProductoRepository productoRepository, CategoriaService categoriaService){
        this.productoRepository = productoRepository;
        this.categoriaService = categoriaService;
    }

    //registrar Producto
    public ProductoDtoResponse registrarProducto(ProductoDtoRequest request){

        Categoria categoria = categoriaService.buscarCategoriaPorId(request.idCategoria());
        Producto producto = new Producto();
        producto.setNombre(request.nombre());
        producto.setCantidad(request.cantidad());
        producto.setPrecio(request.precio());
        producto.setCategoria(categoria);

        Producto productoRegistrado = productoRepository.save(producto);

        return new ProductoDtoResponse(
            productoRegistrado.getId(),
            productoRegistrado.getNombre(),
            productoRegistrado.getCategoria().getId(),
            productoRegistrado.getCantidad(),
            productoRegistrado.getPrecio()
        );
    }


    //buscar producto por id
    public Producto buscarProductoPorId(Long id){
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado")); //runtime temporal
    }


    //actualizar producto
    public ProductoDtoResponse actualizarProducto(Long id, ProductoDtoRequest request){
        Producto producto = buscarProductoPorId(id);
        Categoria categoria = categoriaService.buscarCategoriaPorId(request.idCategoria());

        producto.setNombre(request.nombre());
        producto.setCantidad(request.cantidad());
        producto.setCategoria(categoria);
        producto.setPrecio(request.precio());

        Producto productoActualizado = productoRepository.save(producto);

        return new ProductoDtoResponse(productoActualizado.getId(),
            productoActualizado.getNombre(),
            productoActualizado.getCategoria().getId(),
            productoActualizado.getCantidad(),
            productoActualizado.getPrecio());
    }

    //Eliminar producto
    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
}
