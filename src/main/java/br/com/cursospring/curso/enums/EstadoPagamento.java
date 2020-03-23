package br.com.cursospring.curso.enums;

public enum  EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer codigo;
    private String descricao;

    EstadoPagamento(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static EstadoPagamento toEnum(Integer cod){

        if(cod == null){
            return null;
        }

        for(EstadoPagamento current : EstadoPagamento.values()){

            if(cod.equals(current.getCodigo())){
                return current;
            }

        }

        throw new IllegalArgumentException(String.format("ID %f inválido", cod));

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
