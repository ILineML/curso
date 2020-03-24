package br.com.cursospring.curso.services.validation;

import br.com.cursospring.curso.controllers.exceptions.FieldMessage;
import br.com.cursospring.curso.dto.ClienteNewDto;
import br.com.cursospring.curso.enums.TipoCliente;
import br.com.cursospring.curso.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TipoClienteValidator implements ConstraintValidator<TipoClienteAnnotation, ClienteNewDto> {

    @Override
    public void initialize(TipoClienteAnnotation ann) {
    }

    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCpf(objDto.getDocumento())){
            list.add(new FieldMessage("documento","CPF utilizado é inválido"));
        }

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCpf(objDto.getDocumento())){
            list.add(new FieldMessage("documento","CNPJ utilizado é inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}