package br.com.cursospring.curso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedidoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemPedidoPKEntity id = new ItemPedidoPKEntity();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedidoEntity(){
    }

    public ItemPedidoEntity(PedidoEntity pedido, ProdutoEntity produto, Double desconto, Integer quantidade, Double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @JsonIgnore
    public PedidoEntity getPedido(){
        return this.id.getPedido();
    }

    public ProdutoEntity getProduto(){
        return this.id.getProduto();
    }

    public ItemPedidoPKEntity getId() {
        return id;
    }

    public void setId(ItemPedidoPKEntity id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getSubTotal(){
        return (this.preco - this.desconto) * this.quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoEntity that = (ItemPedidoEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
