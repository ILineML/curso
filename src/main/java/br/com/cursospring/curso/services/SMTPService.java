package br.com.cursospring.curso.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SMTPService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SMTPService.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        mailSender.send(simpleMailMessage);
        LOG.info("EMAIL ENVIADO PARA O EMAIL: " + simpleMailMessage.getTo()[0]);
    }

    @Override
    public void sendHtmlEmail(MimeMessage mimeMessage) throws MessagingException {
        javaMailSender.send(mimeMessage);
        LOG.info("EMAIL ENVIADO PARA O EMAIL: ");
    }

}
