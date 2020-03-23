package br.com.cursospring.curso.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class ProdutoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @ManyToMany
//    Bloqueia busca dos objetos
    @JsonBackReference
    @JoinTable(
            name = "PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "fkProduto"),
            inverseJoinColumns = @JoinColumn(name = "fkCategoria")
    )
    private List<CategoriaEntity> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedidoEntity> itens = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoEntity that = (ProdutoEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public ProdutoEntity() {
    }

    public ProdutoEntity(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }


    public List<PedidoEntity> getPedidos(){

        List<PedidoEntity> lista = new ArrayList<>();

        for(ItemPedidoEntity current : this.itens){
            lista.add(current.getPedido());
        }

        return lista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<CategoriaEntity> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaEntity> categorias) {
        this.categorias = categorias;
    }

    public Set<ItemPedidoEntity> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedidoEntity> itens) {
        this.itens = itens;
    }
}
