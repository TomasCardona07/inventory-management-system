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
    public ProductoDtoResponse registrarProducto(ProductoDtoRequest productoDtoRequest){

        Categoria categoria = categoriaService.buscarCategoriaPorId(productoDtoRequest.idCategoria());
        Producto producto = new Producto();
        producto.setNombre(productoDtoRequest.nombre());
        producto.setCantidad(productoDtoRequest.cantidad());
        producto.setPrecio(productoDtoRequest.precio());
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
}
