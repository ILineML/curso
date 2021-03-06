package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.*;
import br.com.cursospring.curso.enums.EstadoPagamento;
import br.com.cursospring.curso.enums.Perfil;
import br.com.cursospring.curso.repositories.ItemPedidoRepository;
import br.com.cursospring.curso.repositories.PagamentoRepository;
import br.com.cursospring.curso.repositories.PedidoRepository;
import br.com.cursospring.curso.security.UserSecurity;
import br.com.cursospring.curso.services.emails.EmailService;
import br.com.cursospring.curso.services.exceptions.AuthorizationException;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import br.com.cursospring.curso.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public PedidoEntity buscaPedido(Integer id){

        PedidoEntity pedido = pedidoRepository.findById(id).orElse(null);

        if(pedido == null)
            throw new ObjectNotFoundException(String.format("Nenhum registro encontrado para o ID: %d, do tipo %s", id, PedidoEntity.class));

        return pedido;

    }

    public PedidoEntity adicionarPedido(PedidoEntity entity){
        entity.setId(null);
        entity.setInstante(new Date());
        entity.setCliente(clienteService.buscarCliente(entity.getCliente().getId()));


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
            current.setProduto(produtoService.buscaProduto(current.getProduto().getId()));
            current.setPreco(current.getProduto().getPreco());
            current.setPedido(entity);
        }
        itemPedidoRepository.saveAll(entity.getItens());

        emailService.sendOrderConfirmationHtmlEmail(entity);

        return entity;
    }

    public Page<PedidoEntity> encontrarTodasPorPaginaPedido(Integer qtdPagina, Integer linhas, String ordenacao, String direcao){

        UserSecurity userSecurity = UserService.getCurrentUser();

        if(userSecurity == null){
            throw new AuthorizationException("Acesso Negado");
        }

        PageRequest pageRequest = PageRequest.of(qtdPagina, linhas, Sort.Direction.valueOf(direcao), ordenacao);
        ClienteEntity cliente = clienteService.buscarCliente(userSecurity.getId());

        return pedidoRepository.findByCliente(cliente, pageRequest);
    }

}
