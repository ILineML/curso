package br.com.cursospring.curso.services;

import br.com.cursospring.curso.dto.ClienteDTO;
import br.com.cursospring.curso.dto.ClienteNewDTO;
import br.com.cursospring.curso.entities.*;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.enums.TipoCliente;
import br.com.cursospring.curso.repositories.ClienteRepository;
import br.com.cursospring.curso.repositories.EnderecoRepository;
import br.com.cursospring.curso.services.exceptions.DataIntegrityException;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

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
            throw new DataIntegrityException("Não é possível excluir pois há pedidos relacionados a este cliente");
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

    public ClienteEntity converterNewDto(ClienteNewDTO dto){
        ClienteEntity entity = new ClienteEntity(null, dto.getNome(), dto.getEmail(), dto.getDocumento(), TipoCliente.toEnum(dto.getTipoCliente()));
        CidadeEntity cidadeEntity = new CidadeEntity(dto.getCidadeId(), null, null);
        EnderecoEntity enderecoEntity = new EnderecoEntity(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), entity, cidadeEntity);
        entity.getEnderecos().add(enderecoEntity);

        entity.getTelefones().add(dto.getTelefone1());

        if(dto.getTelefone2() != null){
            entity.getTelefones().add(dto.getTelefone3());
        }

        if(dto.getTelefone3() != null){
            entity.getTelefones().add(dto.getTelefone3());
        }

        return entity;
    }

    public ClienteEntity adicionarCliente(ClienteEntity cliente){
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);

        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }


}
