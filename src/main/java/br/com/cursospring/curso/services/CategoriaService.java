package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.repositories.CategoriaRepository;
import br.com.cursospring.curso.services.exceptions.DataIntegrityException;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaEntity buscarCategoria(Integer id){

        CategoriaEntity categoria = categoriaRepository.findById(id).orElse(null);

        if(categoria == null)
            throw new ObjectNotFoundException(String.format("Nenhum registro encontrado para o ID: %d, do tipo %s", id, CategoriaEntity.class));

        return categoria;
    }

    public CategoriaEntity adicionarCategoria(CategoriaEntity categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public CategoriaEntity atualizarCategoria(CategoriaEntity categoria){
        this.buscarCategoria(categoria.getId());
        return categoriaRepository.save(categoria);
    }

    public void deletarCategoria(Integer id){
        this.buscarCategoria(id);

        try{
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex){
                throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }

    }

    public List<CategoriaEntity> encontrarTodasCategorias(){
        return categoriaRepository.findAll();
    }

}
