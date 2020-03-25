package br.com.cursospring.curso.services.emails;

import br.com.cursospring.curso.entities.PedidoEntity;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationHtmlEmail(PedidoEntity pedido);

    void sendHtmlEmail(MimeMessage mimeMessage) throws MessagingException;

}
