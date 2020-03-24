package br.com.cursospring.curso.services;

import br.com.cursospring.curso.dto.ClienteDTO;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.repositories.ClienteRepository;
import br.com.cursospring.curso.services.exceptions.DataIntegrityException;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity buscarCliente(Integer id){

        ClienteEntity cliente = clienteRepository.findById(id).orElse(null);

        if(cliente == null)
            throw new ObjectNotFoundException(String.format("Nenhum registro encontrado para o ID: %d, do tipo %s", id, ClienteEntity.class));

        return cliente;

    }

    public ClienteEntity atualizarCliente(ClienteEntity cliente){
        ClienteEntity entity = this.buscarCliente(cliente.getId());
        this.atualizarDados(entity, cliente);
        return clienteRepository.save(entity);
    }

    public void deletarCliente(Integer id){
        this.buscarCliente(id);

        try{
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não é possível excluir pois há entidades relacionadas");
        }

    }

    public List<ClienteEntity> encontrarTodasClientes(){
        return clienteRepository.findAll();
    }

    public Page<ClienteEntity> encontrarTodasPorPaginaClientes(Integer qtdPagina, Integer linhas, String ordenacao, String direcao){
        PageRequest pageRequest = PageRequest.of(qtdPagina, linhas, Sort.Direction.valueOf(direcao), ordenacao);
        return clienteRepository.findAll(pageRequest);

    }

    private void atualizarDados(ClienteEntity entity, ClienteEntity cliente){
        entity.setNome(cliente.getNome());
        entity.setEmail(cliente.getEmail());
    }

    public ClienteEntity converterDto(ClienteDTO dto){
        return new ClienteEntity(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
    }

}
