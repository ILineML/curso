package br.com.cursospring.curso.repositories;

import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.entities.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    @Transactional(readOnly = true)
    Page<PedidoEntity> findByCliente(ClienteEntity cliente, Pageable pageable);

}
