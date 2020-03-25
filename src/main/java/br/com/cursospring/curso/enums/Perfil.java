package br.com.cursospring.curso.enums;

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int codigo;
    private String descricao;

    Perfil(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod){

        if(cod == null){
            return null;
        }

        for(Perfil current : Perfil.values()){

            if(cod.equals(current.getCodigo())){
                return current;
            }

        }

        throw new IllegalArgumentException(String.format("ID %f inválido", cod));

    }

}
