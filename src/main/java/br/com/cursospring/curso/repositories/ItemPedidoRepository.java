package br.com.cursospring.curso.repositories;

import br.com.cursospring.curso.entities.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Integer> {
}
