package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.PedidoEntity;
import br.com.cursospring.curso.services.CategoriaService;
import br.com.cursospring.curso.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
