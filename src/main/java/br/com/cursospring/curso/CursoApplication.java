package br.com.cursospring.curso;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CategoriaEntity cat1 = new CategoriaEntity(null, "Informática");
		CategoriaEntity cat2 = new CategoriaEntity(null, "Escritório");

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));

	}

}

