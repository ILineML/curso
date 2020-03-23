package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.repositories.CategoriaRepository;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
