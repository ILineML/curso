package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.dto.CategoriaDTO;
import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.PedidoEntity;
import br.com.cursospring.curso.services.CategoriaService;
import br.com.cursospring.curso.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PedidoEntity> encontrarCategoria(@PathVariable Integer id){

        PedidoEntity pedido = pedidoService.buscaPedido(id);

        return ResponseEntity.ok().body(pedido);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarCategoria(@Valid @RequestBody PedidoEntity body){

        PedidoEntity pedido = pedidoService.adicionarPedido(body);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
