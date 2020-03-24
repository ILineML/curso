package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.PedidoEntity;
import br.com.cursospring.curso.entities.ProdutoEntity;
import br.com.cursospring.curso.repositories.CategoriaRepository;
import br.com.cursospring.curso.repositories.PedidoRepository;
import br.com.cursospring.curso.repositories.ProdutosRepository;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoEntity buscaProduto(Integer id){

        ProdutoEntity produto = produtosRepository.findById(id).orElse(null);

        if(produto == null)
            throw new ObjectNotFoundException(String.format("Nenhum registro encontrado para o ID: %d, do tipo %s", id, PedidoEntity.class));

        return produto;

    }

    public Page<ProdutoEntity> pesquisarProduto(String nome, List<Integer> ids, Integer qtdPagina, Integer linhas, String ordenacao, String direcao){
        PageRequest pageRequest = PageRequest.of(qtdPagina, linhas, Sort.Direction.valueOf(direcao), ordenacao);
        List<CategoriaEntity> categorias = categoriaRepository.findAllById(ids);

        return produtosRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }




}
