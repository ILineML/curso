package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.*;
import br.com.cursospring.curso.enums.EstadoPagamento;
import br.com.cursospring.curso.enums.Perfil;
import br.com.cursospring.curso.enums.TipoCliente;
import br.com.cursospring.curso.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() throws ParseException {

        CategoriaEntity cat1 = new CategoriaEntity(null, "Informática");
        CategoriaEntity cat2 = new CategoriaEntity(null, "Escritório");
        CategoriaEntity cat3 = new CategoriaEntity(null, "Cama, mesa e banho");
        CategoriaEntity cat4 = new CategoriaEntity(null, "Eletrônicos");
        CategoriaEntity cat5 = new CategoriaEntity(null, "Jardinagem");
        CategoriaEntity cat6 = new CategoriaEntity(null, "Decoração");
        CategoriaEntity cat7 = new CategoriaEntity(null, "Perfumaria");

        ProdutoEntity p1 = new ProdutoEntity(null, "Computador", 2000.50);
        ProdutoEntity p2 = new ProdutoEntity(null, "Impressora", 800.00);
        ProdutoEntity p3 = new ProdutoEntity(null, "Mouse", 80.10);
        ProdutoEntity p4 = new ProdutoEntity(null, "Mesa de escritório", 80.10);
        ProdutoEntity p5 = new ProdutoEntity(null, "Toalha", 80.10);
        ProdutoEntity p6 = new ProdutoEntity(null, "Colcha", 80.10);
        ProdutoEntity p7 = new ProdutoEntity(null, "Tv true color", 80.10);
        ProdutoEntity p8 = new ProdutoEntity(null, "Roçadeira", 80.10);
        ProdutoEntity p9 = new ProdutoEntity(null, "Abajour", 80.10);
        ProdutoEntity p10 = new ProdutoEntity(null, "Condicionador", 80.10);
        ProdutoEntity p11 = new ProdutoEntity(null, "Shampoo", 80.10);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3, p7));
        cat5.getProdutos().add(p8);
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().add(p11);

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().add(cat2);
        p5.getCategorias().add(cat3);
        p6.getCategorias().add(cat3);
        p7.getCategorias().add(cat4);
        p8.getCategorias().add(cat5);
        p9.getCategorias().add(cat6);
        p10.getCategorias().add(cat6);
        p11.getCategorias().add(cat7);

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
        produtosRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        EstadoEntity est1 = new EstadoEntity(null, "Minas Gerais");
        EstadoEntity est2 = new EstadoEntity(null, "São Paulo");

        CidadeEntity c1 = new CidadeEntity(null,"Uberlândia", est1);
        CidadeEntity c2 = new CidadeEntity(null,"São Paulo", est2);
        CidadeEntity c3 = new CidadeEntity(null,"Campinas", est2);

        est1.getCidades().add(c1);
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));


        ClienteEntity cli1 = new ClienteEntity(null, "Matheus Lemes", "theilemes@gmail.com", "37762756850", TipoCliente.PESSOA_FISICA, bCryptPasswordEncoder.encode("senha123"));
        cli1.getTelefones().addAll(Arrays.asList("11977202265", "1123012143"));

        ClienteEntity cli2 = new ClienteEntity(null, "Matheus Admin", "matheus.leme@partners.digital", "37762756850", TipoCliente.PESSOA_FISICA, bCryptPasswordEncoder.encode("nimda"));
        cli2.addPerfil(Perfil.ADMIN);
        cli2.getTelefones().addAll(Arrays.asList("11977202265", "1123012143"));

        EnderecoEntity e1 = new EnderecoEntity(null, "Padre Bruno Ricco","458",null,"Padre Bruno Ricco","0323850", cli1, c2);
        EnderecoEntity e2 = new EnderecoEntity(null, "Avenida Matos","105","Casa B","Costa Oeste","12365478", cli1, c1);
        EnderecoEntity e3 = new EnderecoEntity(null, "Avenida Floriano","205",null,"Costa Leste","12645478", cli2, c1);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        PedidoEntity ped1 = new PedidoEntity(null, sdf.parse("30/09/2017 18:02:30"), e1, cli1);
        PedidoEntity ped2 = new PedidoEntity(null, sdf.parse("04/19/2019 20:12:40"), e1, cli1);

        PagamentoEntity pag1 = new PagamentoCartaoEntity(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pag1);

        PagamentoEntity pag2 = new PagamentoBoletoEntity(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/03/2020 23:59:59"), null);
        ped2.setPagamento(pag2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

        ItemPedidoEntity ip1 = new ItemPedidoEntity(ped1, p1, 0.00, 1, 2000.00);
        ItemPedidoEntity ip2 = new ItemPedidoEntity(ped1, p3, 0.00, 2, 80.00);
        ItemPedidoEntity ip3 = new ItemPedidoEntity(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }

}
