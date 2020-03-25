package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.dto.ClienteDTO;
import br.com.cursospring.curso.dto.ClienteNewDTO;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PreAuthorize("HasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id){
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("HasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> encontrarTodasCliente(){

        List<ClienteEntity> clientes = clienteService.encontrarTodasClientes();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(entity -> new ClienteDTO(entity)).collect(Collectors.toList());

        return ResponseEntity.ok().body(clienteDTOS);
    }

    @PreAuthorize("HasAnyRole('ADMIN')")
    @RequestMapping(value = "/pagina", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> encontrarTodasPorPaginaCliente(
            @RequestParam(defaultValue = "0") Integer qtdPagina, @RequestParam(defaultValue = "24") Integer linhas,
            @RequestParam(defaultValue = "nome") String ordenacao, @RequestParam(defaultValue = "ASC") String direcao
    ){

        Page<ClienteEntity> clientes = clienteService.encontrarTodasPorPaginaClientes(qtdPagina, linhas, ordenacao, direcao);
        Page<ClienteDTO> clienteDTOS = clientes.map(entity -> new ClienteDTO(entity));

        return ResponseEntity.ok().body(clienteDTOS);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarCliente(@Valid @RequestBody ClienteNewDTO body){
        ClienteEntity entity = clienteService.converterNewDto(body);
        ClienteEntity categoria = clienteService.adicionarCliente(entity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/imagem", method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarCliente(@RequestBody MultipartFile file){
//        A PARTIR DAQUI, DEVE SER CRIADA A SERVICE QUE IRA SALVAR ESTA IMAGEM NA NUVEM
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(file.getName()).toUri();

        return ResponseEntity.created(uri).build();
    }


}
