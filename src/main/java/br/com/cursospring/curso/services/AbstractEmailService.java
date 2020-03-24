package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(PedidoEntity pedido){
        SimpleMailMessage sm = prepareSimpleMailMessage(pedido);
        sendEmail(sm);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(PedidoEntity pedido){
        try {
            MimeMessage mm = prepareMimeMessageFromPedido(pedido);
            sendHtmlEmail(mm);
        } catch (MessagingException me){
            System.out.println("Deu ruim");
        }

    }

    protected SimpleMailMessage prepareSimpleMailMessage(PedidoEntity pedidoEntity){
        SimpleMailMessage sm = new SimpleMailMessage();

        sm.setTo(pedidoEntity.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmato - Código " + pedidoEntity.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));

        sm.setText(pedidoEntity.toString());

        return sm;
    }

    protected MimeMessage prepareMimeMessageFromPedido(PedidoEntity pedido) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();

        MimeMessageHelper mmH = new MimeMessageHelper(mm, true);
        mmH.setTo(pedido.getCliente().getEmail());
        mmH.setFrom(sender);
        mmH.setSubject("Pedido confirmato - Código " + pedido.getId());
        mmH.setSentDate(new Date(System.currentTimeMillis()));
        mmH.setText(htmlFromTemplateFromPedido(pedido), true);

        return mm;
    }

    protected String htmlFromTemplateFromPedido(PedidoEntity pedidoEntity){
        Context context = new Context();
        context.setVariable("pedido", pedidoEntity);
        return templateEngine.process("email/confirmacaoPedido", context);
    }

}
