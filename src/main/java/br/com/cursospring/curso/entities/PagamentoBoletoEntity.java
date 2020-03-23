package br.com.cursospring.curso.entities;

import br.com.cursospring.curso.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoBoletoEntity extends PagamentoEntity {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dtVencimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dtPagamento;

    public PagamentoBoletoEntity(){
    }

    public PagamentoBoletoEntity(Integer id, EstadoPagamento estado, PedidoEntity pedido, Date dtVencimento, Date dtPagamento) {
        super(id, estado, pedido);
        this.dtVencimento = dtVencimento;
        this.dtPagamento = dtPagamento;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

}
