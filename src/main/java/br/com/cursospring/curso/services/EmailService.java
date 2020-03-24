package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.PedidoEntity;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(PedidoEntity pedido);

    void sendEmail(SimpleMailMessage simpleMailMessage);

    void sendOrderConfirmationHtmlEmail(PedidoEntity pedido);

    void sendHtmlEmail(MimeMessage mimeMessage) throws MessagingException;

}
