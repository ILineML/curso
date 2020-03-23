package br.com.cursospring.curso.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoPKEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "fkPedido")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "fkProduto")
    private ProdutoEntity produto;

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPKEntity that = (ItemPedidoPKEntity) o;
        return pedido.equals(that.pedido) &&
                produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }

}
