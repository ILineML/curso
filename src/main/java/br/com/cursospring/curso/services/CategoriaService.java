package br.com.cursospring.curso.services;

import br.com.cursospring.curso.dto.CategoriaDTO;
import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.repositories.CategoriaRepository;
import br.com.cursospring.curso.services.exceptions.DataIntegrityException;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        CategoriaEntity entity = this.buscarCategoria(categoria.getId());
        this.atualizarDados(entity, categoria);
        return categoriaRepository.save(entity);
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

    public Page<CategoriaEntity> encontrarTodasPorPaginaCategorias(Integer qtdPagina, Integer linhas, String ordenacao, String direcao){
        PageRequest pageRequest = PageRequest.of(qtdPagina, linhas, Sort.Direction.valueOf(direcao), ordenacao);
        return categoriaRepository.findAll(pageRequest);

    }

    public CategoriaEntity converterDto(CategoriaDTO dto){
        return new CategoriaEntity(dto.getId(), dto.getNome());
    }

    private void atualizarDados(CategoriaEntity entity, CategoriaEntity categoria){
        entity.setNome(categoria.getNome());
    }

}
