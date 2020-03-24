package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.PedidoEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(PedidoEntity pedido){
        SimpleMailMessage sm = prepareSimpleMailMessage(pedido);
        sendEmail(sm);
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

}
