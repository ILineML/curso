package br.com.cursospring.curso;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.CidadeEntity;
import br.com.cursospring.curso.entities.EstadoEntity;
import br.com.cursospring.curso.entities.ProdutoEntity;
import br.com.cursospring.curso.repositories.CategoriaRepository;
import br.com.cursospring.curso.repositories.CidadeRepository;
import br.com.cursospring.curso.repositories.EstadoRepository;
import br.com.cursospring.curso.repositories.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}

}

