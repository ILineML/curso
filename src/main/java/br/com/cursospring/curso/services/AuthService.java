package br.com.cursospring.curso.services;

import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.repositories.ClienteRepository;
import br.com.cursospring.curso.services.emails.EmailService;
import br.com.cursospring.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email){

        ClienteEntity clienteEntity = clienteRepository.findByEmail(email);

        if(clienteEntity == null){
            throw  new ObjectNotFoundException("Email não encontrado");
        }

        String pass = newPassword();
        clienteEntity.setSenha(bCryptPasswordEncoder.encode(pass));

        clienteRepository.save(clienteEntity);
        emailService.sendNewPasswordEmail(clienteEntity, pass);

    }

    private String newPassword(){
        char[] chars = new char[10];

        for (int i = 0; i < 10; i++){
            chars[i] = generateChart();
        }

        return chars.toString();
    }

    private char generateChart(){
        int opt  = random.nextInt(3);

        if(opt == 0){
            return (char) (random.nextInt(10) + 48);
        } else if(opt == 1){
            return (char) (random.nextInt(26) + 65);
        }

        return (char) (random.nextInt(26) + 97);

    }

}
