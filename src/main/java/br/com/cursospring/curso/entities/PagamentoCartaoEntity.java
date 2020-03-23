package br.com.cursospring.curso.entities;

import br.com.cursospring.curso.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
public class PagamentoCartaoEntity extends PagamentoEntity {
    private static final long serialVersionUID = 1L;

    private Integer numParcelas;

    public PagamentoCartaoEntity(){
    }

    public PagamentoCartaoEntity(Integer id, EstadoPagamento estado, PedidoEntity pedido, Integer numParcelas) {
        super(id, estado, pedido);
        this.numParcelas = numParcelas;
    }

    public Integer getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(Integer numParcelas) {
        this.numParcelas = numParcelas;
    }
}
