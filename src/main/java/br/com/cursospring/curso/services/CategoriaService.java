package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaEntity buscarCategoria(Integer id){
        return categoriaRepository.findById(id).orElse(null);
    }

}
