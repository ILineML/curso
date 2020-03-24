package br.com.cursospring.curso.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String mensagem, Long timeStamp) {
        super(status, mensagem, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return this.errors;
    }

    public void adicionarError(String campo, String mensagem) {
        this.errors.add(new FieldMessage(campo, mensagem));
    }

}
