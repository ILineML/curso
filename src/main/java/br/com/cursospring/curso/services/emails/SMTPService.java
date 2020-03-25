package br.com.cursospring.curso.services.emails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SMTPService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SMTPService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendHtmlEmail(MimeMessage mimeMessage) {
        javaMailSender.send(mimeMessage);
        LOG.info("EMAIL ENVIADO PARA O EMAIL: ");
    }

}
