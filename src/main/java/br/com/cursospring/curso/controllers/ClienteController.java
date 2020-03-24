package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.dto.ClienteDTO;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.services.ClienteService;
import br.com.cursospring.curso.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClienteEntity> encontrarCliente(@PathVariable Integer id){

        ClienteEntity cliente = clienteService.buscarCliente(id);

        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizarCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO body){
        body.setId(id);
        ClienteEntity entity = clienteService.converterDto(body);
        ClienteEntity cliente = clienteService.atualizarCliente(entity);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> encontrarTodasCliente(){

        List<ClienteEntity> clientes = clienteService.encontrarTodasClientes();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(entity -> new ClienteDTO(entity)).collect(Collectors.toList());

        return ResponseEntity.ok().body(clienteDTOS);
    }

    @RequestMapping(value = "/pagina", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> encontrarTodasPorPaginaCliente(
            @RequestParam(defaultValue = "0") Integer qtdPagina, @RequestParam(defaultValue = "24") Integer linhas,
            @RequestParam(defaultValue = "nome") String ordenacao, @RequestParam(defaultValue = "ASC") String direcao
    ){

        Page<ClienteEntity> clientes = clienteService.encontrarTodasPorPaginaClientes(qtdPagina, linhas, ordenacao, direcao);
        Page<ClienteDTO> clienteDTOS = clientes.map(entity -> new ClienteDTO(entity));

        return ResponseEntity.ok().body(clienteDTOS);
    }



}
