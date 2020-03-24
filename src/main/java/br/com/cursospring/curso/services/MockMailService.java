package br.com.cursospring.curso.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

public class MockMailService extends AbstractEmailService{

    private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);
    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Simulação de envio de email");
        LOG.info(simpleMailMessage.toString());
        LOG.info("Email Enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage mimeMessage) {
        LOG.info("Simulação de envio de email");
        LOG.info(mimeMessage.toString());
        LOG.info("Email Enviado");
    }

}
