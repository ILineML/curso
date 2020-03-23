package br.com.cursospring.curso.repositories;

import br.com.cursospring.curso.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutoEntity, Integer> {
}
