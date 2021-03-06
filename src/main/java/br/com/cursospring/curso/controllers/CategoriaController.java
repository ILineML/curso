package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.dto.CategoriaDTO;
import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoriaEntity> encontrarCategoria(@PathVariable Integer id){

        CategoriaEntity categoria = categoriaService.buscarCategoria(id);

        return ResponseEntity.ok().body(categoria);
    }

    @PreAuthorize("HasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarCategoria(@Valid @RequestBody CategoriaDTO body){
        CategoriaEntity entity = categoriaService.converterDto(body);
        CategoriaEntity categoria = categoriaService.adicionarCategoria(entity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("HasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizarCategoria(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO body){
        body.setId(id);
        CategoriaEntity entity = categoriaService.converterDto(body);
        CategoriaEntity categoria = categoriaService.atualizarCategoria(entity);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("HasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> encontrarTodasCategoria(){

        List<CategoriaEntity> categorias = categoriaService.encontrarTodasCategorias();
        List<CategoriaDTO> categoriaDTOS = categorias.stream().map(entity -> new CategoriaDTO(entity)).collect(Collectors.toList());

        return ResponseEntity.ok().body(categoriaDTOS);
    }

    @RequestMapping(value = "/pagina", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> encontrarTodasPorPaginaCategoria(
            @RequestParam(defaultValue = "0") Integer qtdPagina, @RequestParam(defaultValue = "24") Integer linhas,
            @RequestParam(defaultValue = "nome") String ordenacao, @RequestParam(defaultValue = "ASC") String direcao
    ){

        Page<CategoriaEntity> categorias = categoriaService.encontrarTodasPorPaginaCategorias(qtdPagina, linhas, ordenacao, direcao);
        Page<CategoriaDTO> categoriaDTOS = categorias.map(entity -> new CategoriaDTO(entity));

        return ResponseEntity.ok().body(categoriaDTOS);
    }


}
