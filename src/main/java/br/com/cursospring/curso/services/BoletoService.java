package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.PagamentoBoletoEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoBoleto(PagamentoBoletoEntity boleto, Date instante){

        Calendar cal = Calendar.getInstance();
        cal.setTime(instante);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        boleto.setDtVencimento(cal.getTime());

    }
}
