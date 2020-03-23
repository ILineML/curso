package br.com.cursospring.curso.enums;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod){

        if(cod == null){
            return null;
        }

        for(TipoCliente current : TipoCliente.values()){

            if(cod.equals(current)){
                return current;
            }

        }

        throw new IllegalArgumentException(String.format("ID %f inválido", cod));

    }

}
