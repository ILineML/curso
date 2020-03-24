package br.com.cursospring.curso.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class PedidoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date instante;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private PagamentoEntity pagamento;

    @ManyToOne
    @JoinColumn(name = "fkEntrega")
    private EnderecoEntity enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "fkCliente")
//    @JsonManagedReference
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedidoEntity> itens = new HashSet<>();

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

    public Set<ItemPedidoEntity> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedidoEntity> itens) {
        this.itens = itens;
    }

    public Double getValorTotal(){

        double soma = 0;

        for(ItemPedidoEntity current : this.getItens()){
            soma += current.getSubTotal();
        }

        return soma;

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

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        final StringBuilder sb = new StringBuilder("PedidoEntity{");
        sb.append("Pedido número:   ").append(this.id);
        sb.append(", Instante: ");
        sb.append(sdf.format(this.instante));
        sb.append(", Cliente: ");
        sb.append(this.cliente.getNome());
        sb.append(", Situação do pagamento: ");
        sb.append(this.pagamento.getEstado().getDescricao());
        sb.append("\n Detalhes: \n");

        for(ItemPedidoEntity current : this.itens){
            sb.append(current);
        }

        sb.append("\n Valor Total: ");
        sb.append(nf.format(this.getValorTotal()));

        return sb.toString();
    }
}
