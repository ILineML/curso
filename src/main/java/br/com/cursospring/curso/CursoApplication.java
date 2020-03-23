package br.com.cursospring.curso;

import br.com.cursospring.curso.entities.*;
import br.com.cursospring.curso.enums.EstadoPagamento;
import br.com.cursospring.curso.enums.TipoCliente;
import br.com.cursospring.curso.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursoApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CategoriaEntity cat1 = new CategoriaEntity(null, "Informática");
		CategoriaEntity cat2 = new CategoriaEntity(null, "Escritório");

		ProdutoEntity p1 = new ProdutoEntity(null, "Computador", 2000.50);
		ProdutoEntity p2 = new ProdutoEntity(null, "Impressora", 800.00);
		ProdutoEntity p3 = new ProdutoEntity(null, "Mouse", 80.10);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtosRepository.saveAll(Arrays.asList(p1, p2, p3));

		EstadoEntity est1 = new EstadoEntity(null, "Minas Gerais");
		EstadoEntity est2 = new EstadoEntity(null, "São Paulo");

		CidadeEntity c1 = new CidadeEntity(null,"Uberlândia", est1);
		CidadeEntity c2 = new CidadeEntity(null,"São Paulo", est2);
		CidadeEntity c3 = new CidadeEntity(null,"Campinas", est2);

		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));


		ClienteEntity cli1 = new ClienteEntity(null, "Matheus Lemes", "m@gmail.com", "37762756850", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("11977202265", "1123012143"));

		EnderecoEntity e1 = new EnderecoEntity(null, "Padre Bruno Ricco","458",null,"Padre Bruno Ricco","0323850", cli1, c2);
		EnderecoEntity e2 = new EnderecoEntity(null, "Avenida Matos","105","Casa B","Costa Oeste","12365478", cli1, c1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(cli1);
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

