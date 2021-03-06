package br.com.cursospring.curso.repositories;

import br.com.cursospring.curso.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Transactional(readOnly = true)
    ClienteEntity findByEmail(String email);

}
