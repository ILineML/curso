package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.dto.CategoriaDTO;
import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarCategoria(@RequestBody CategoriaEntity body){
        CategoriaEntity categoria = categoriaService.adicionarCategoria(body);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaEntity body){
        body.setId(id);
        CategoriaEntity categoria = categoriaService.atualizarCategoria(body);
        return ResponseEntity.noContent().build();
    }

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

}
