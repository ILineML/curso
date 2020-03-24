package br.com.cursospring.curso.dto;

import br.com.cursospring.curso.entities.CategoriaEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Nome não pode ser nulo")
    @Length(min = 5, max = 80, message = "Nome deve conter entre 5 a 80 caracteres")
    private String nome;

    public CategoriaDTO(){
    }

    public CategoriaDTO(CategoriaEntity entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
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

}
