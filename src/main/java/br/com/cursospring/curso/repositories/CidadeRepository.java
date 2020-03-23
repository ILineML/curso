package br.com.cursospring.curso.repositories;

import br.com.cursospring.curso.entities.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Integer> {
}
