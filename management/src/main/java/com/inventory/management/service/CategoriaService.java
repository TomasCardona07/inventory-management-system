package com.inventory.management.service;
import com.inventory.management.model.Categoria;
import java.util.List;
import org.springframework.stereotype.Service;
import com.inventory.management.dto.CategoriaDtoRequest;
import com.inventory.management.dto.CategoriaDtoResponse;
import com.inventory.management.exception.CategoriaNoEncontradaException;
import com.inventory.management.repository.CategoriaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    //convertir categoria a dtoResponse
    private  CategoriaDtoResponse convertirADTO(Categoria categoria){
        return new CategoriaDtoResponse(categoria.getId(), categoria.getNombre());
    }

    //Registrar Categoria
    public CategoriaDtoResponse registrarCategoria(CategoriaDtoRequest categoriaDtoRequest){
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDtoRequest.nombre());
        Categoria categoriaRegistrada = categoriaRepository.save(categoria);
        
        return convertirADTO(categoriaRegistrada);
    }

    //consultar todas las categorias
    public List<CategoriaDtoResponse> consultarCategorias(){
        return categoriaRepository.findAll()
            .stream()
            .map(categoria -> convertirADTO(categoria))
            .toList();
    }


    //Actualizar categoria
    @Transactional 
    public CategoriaDtoResponse actualizarCategoria(Long id, CategoriaDtoRequest request){
        Categoria categoria = obtenerCategoriaPorId(id);
        categoria.setNombre(request.nombre());
        return convertirADTO(categoria);
    }


    //eliminar categoria por id
    public void eliminarCategoriaPorId(Long id){
        Categoria categoria = obtenerCategoriaPorId(id);
        categoriaRepository.delete(categoria);
    }
    


    //obtener categoria por id
    public Categoria obtenerCategoriaPorId(Long id){ //retorna entidad
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new CategoriaNoEncontradaException("Categoria no encontrada"));
    }

    public CategoriaDtoResponse buscarCategoriaPorId(Long id){ //retorna DTO
        Categoria categoria = obtenerCategoriaPorId(id);
        return convertirADTO(categoria);
    }
}
