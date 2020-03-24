package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.ItemPedidoEntity;
import br.com.cursospring.curso.entities.PagamentoBoletoEntity;
import br.com.cursospring.curso.entities.PedidoEntity;
import br.com.cursospring.curso.enums.EstadoPagamento;
import br.com.cursospring.curso.repositories.ItemPedidoRepository;
import br.com.cursospring.curso.repositories.PagamentoRepository;
import br.com.cursospring.curso.repositories.PedidoRepository;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoEntity buscaPedido(Integer id){

        PedidoEntity pedido = pedidoRepository.findById(id).orElse(null);

        if(pedido == null)
            throw new ObjectNotFoundException(String.format("Nenhum registro encontrado para o ID: %d, do tipo %s", id, PedidoEntity.class));

        return pedido;

    }

    public PedidoEntity adicionarPedido(PedidoEntity entity){
        entity.setId(null);
        entity.setInstante(new Date());

        entity.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        entity.getPagamento().setPedido(entity);

        if(entity.getPagamento() instanceof PagamentoBoletoEntity){
            PagamentoBoletoEntity pagto = (PagamentoBoletoEntity) entity.getPagamento();
            boletoService.preencherPagamentoBoleto(pagto, entity.getInstante());
        }

        entity = pedidoRepository.save(entity);
        pagamentoRepository.save(entity.getPagamento());

        for(ItemPedidoEntity current : entity.getItens()){
            current.setDesconto(0.00);
            current.setPreco(produtoService.buscaProduto(current.getProduto().getId()).getPreco());
            current.setPedido(entity);
        }
        itemPedidoRepository.saveAll(entity.getItens());
        return entity;
    }

}
