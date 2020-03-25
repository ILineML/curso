package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.dto.CategoriaDTO;
import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.PedidoEntity;
import br.com.cursospring.curso.services.CategoriaService;
import br.com.cursospring.curso.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<PedidoEntity> encontrarPedido(@PathVariable Integer id){

        PedidoEntity pedido = pedidoService.buscaPedido(id);

        return ResponseEntity.ok().body(pedido);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarPedido(@Valid @RequestBody PedidoEntity body){

        PedidoEntity pedido = pedidoService.adicionarPedido(body);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<PedidoEntity>> encontrarTodasPorPaginaPedido(
            @RequestParam(defaultValue = "0") Integer qtdPagina, @RequestParam(defaultValue = "24") Integer linhas,
            @RequestParam(defaultValue = "instante") String ordenacao, @RequestParam(defaultValue = "DESC") String direcao
    ){

        Page<PedidoEntity> categorias = pedidoService.encontrarTodasPorPaginaPedido(qtdPagina, linhas, ordenacao, direcao);

        return ResponseEntity.ok().body(categorias);
    }


}
