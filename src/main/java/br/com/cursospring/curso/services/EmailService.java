package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.PedidoEntity;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(PedidoEntity pedido);

    void sendEmail(SimpleMailMessage simpleMailMessage);

}
