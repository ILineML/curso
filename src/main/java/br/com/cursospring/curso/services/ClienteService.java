package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.repositories.ClienteRepository;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity buscaCliente(Integer id){

        ClienteEntity cliente = clienteRepository.findById(id).orElse(null);

        if(cliente == null)
            throw new ObjectNotFoundException(String.format("Nenhum registro encontrado para o ID: %d, do tipo %s", id, ClienteEntity.class));

        return cliente;

    }

}
