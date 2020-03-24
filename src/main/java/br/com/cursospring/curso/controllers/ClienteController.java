package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.services.CategoriaService;
import br.com.cursospring.curso.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClienteEntity> encontrarCategoria(@PathVariable Integer id){

        ClienteEntity cliente = clienteService.buscaCliente(id);

        return ResponseEntity.ok().body(cliente);
    }


}
