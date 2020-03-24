package br.com.cursospring.curso.config;

import br.com.cursospring.curso.services.DBService;
import br.com.cursospring.curso.services.EmailService;
import br.com.cursospring.curso.services.MockMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if(strategy.equals("create")){
            dbService.instantiateTestDatabase();
        }

        return true;
    }

    @Bean
    public EmailService mockService(){
        return new MockMailService();
    }


}
