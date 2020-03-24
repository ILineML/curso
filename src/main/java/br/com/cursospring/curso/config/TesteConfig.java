package br.com.cursospring.curso.config;

import br.com.cursospring.curso.services.DBService;
import br.com.cursospring.curso.services.EmailService;
import br.com.cursospring.curso.services.MockMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("teste")
public class TesteConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService mockService(){
        return new MockMailService();
    }

}
