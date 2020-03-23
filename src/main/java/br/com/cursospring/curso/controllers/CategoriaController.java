package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.entitys.CategoriaEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoriaEntity> listarCategorias(){

        CategoriaEntity c1 = new CategoriaEntity(1, "Informática");
        CategoriaEntity c2 = new CategoriaEntity(2, "Escritório");

        List<CategoriaEntity> categoriaist = new ArrayList<>();
        categoriaist.add(c1);
        categoriaist.add(c2);

        return categoriaist;
    }

}
