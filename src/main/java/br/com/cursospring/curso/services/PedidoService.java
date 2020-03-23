package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.entities.PedidoEntity;
import br.com.cursospring.curso.repositories.ClienteRepository;
import br.com.cursospring.curso.repositories.PedidoRepository;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoEntity buscaPedido(Integer id){

        PedidoEntity pedido = pedidoRepository.findById(id).orElse(null);

        if(pedido == null)
            throw new ObjectNotFoundException(String.format("Nenhum registro encontrado para o ID: %d, do tipo %s", id, PedidoEntity.class));

        return pedido;

    }

}
