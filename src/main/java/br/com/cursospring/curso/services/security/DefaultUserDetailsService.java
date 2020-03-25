package br.com.cursospring.curso.services.security;

import br.com.cursospring.curso.entities.ClienteEntity;
import br.com.cursospring.curso.repositories.ClienteRepository;
import br.com.cursospring.curso.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//        Encontra seu usário a partir do email
        ClienteEntity clienteEntity = clienteRepository.findByEmail(email);

        if(clienteEntity == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSecurity(clienteEntity.getId(), clienteEntity.getEmail(), clienteEntity.getSenha(), clienteEntity.getPerfil());
    }

}
