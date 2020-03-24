package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> encontrarCategoria(@PathVariable Integer id){

        CategoriaEntity categoria = categoriaService.buscarCategoria(id);

        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> adiciionarCategoria(@RequestBody CategoriaEntity body){
        CategoriaEntity categoria = categoriaService.adicionarCategoria(body);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


}
