package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.controllers.utils.URL;
import br.com.cursospring.curso.dto.ProdutoDTO;
import br.com.cursospring.curso.entities.ProdutoEntity;
import br.com.cursospring.curso.entities.ProdutoEntity;
import br.com.cursospring.curso.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProdutoEntity> encontrarProduto(@PathVariable Integer id){

        ProdutoEntity produto = produtoService.buscaProduto(id);

        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> encontrarTodasPorPaginaProduto(
            @RequestParam(defaultValue = "") String nome, @RequestParam(defaultValue = "") String categorias,
            @RequestParam(defaultValue = "0") Integer qtdPagina, @RequestParam(defaultValue = "24") Integer linhas,
            @RequestParam(defaultValue = "nome") String ordenacao, @RequestParam(defaultValue = "ASC") String direcao
    ){
        List<Integer> ids = URL.decodeIds(categorias);
        String decoded = URL.decodeParametro(nome);
        Page<ProdutoEntity> produtos = produtoService.pesquisarProduto(decoded, ids, qtdPagina, linhas, ordenacao, direcao);
        Page<ProdutoDTO> produtoDTOS = produtos.map(entity -> new ProdutoDTO(entity));

        return ResponseEntity.ok().body(produtoDTOS);
    }

}
