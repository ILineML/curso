package br.com.cursospring.curso.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class PedidoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date instante;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private PagamentoEntity pagamento;

    @ManyToOne
    @JoinColumn(name = "fkEntrega")
    private EnderecoEntity enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "fkCliente")
    private ClienteEntity cliente;

    public PedidoEntity(){
    }

    public PedidoEntity(Integer id, Date instante, EnderecoEntity enderecoEntrega, ClienteEntity cliente) {
        this.id = id;
        this.instante = instante;
        this.enderecoEntrega = enderecoEntrega;
        this.cliente = cliente;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public PagamentoEntity getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoEntity pagamento) {
        this.pagamento = pagamento;
    }

    public EnderecoEntity getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntity enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoEntity that = (PedidoEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
