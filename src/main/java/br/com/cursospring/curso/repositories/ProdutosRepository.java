package br.com.cursospring.curso.repositories;

import br.com.cursospring.curso.entities.CategoriaEntity;
import br.com.cursospring.curso.entities.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutoEntity, Integer> {

//    @Query("SELECT DISTINCT obj FROM ProdutoEntity obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome AND cat IN :categorias")
//    Page<ProdutoEntity> pesquisarProdutos(@Param("nome") String nome, @Param("categorias") List<CategoriaEntity >categorias, Pageable pageRequest);
//    ESSA MESMA CONSULTA PODE SER FEITA APENAS UTILIZANDO ESTE PADRÃO DE NOME:
    @Transactional(readOnly = true)
    Page<ProdutoEntity> findDistinctByNomeContainingAndCategoriasIn(String nome, List<CategoriaEntity> categoria, Pageable pageRequest);


}
