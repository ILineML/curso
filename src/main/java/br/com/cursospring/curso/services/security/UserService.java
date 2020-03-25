package br.com.cursospring.curso.services.security;

import br.com.cursospring.curso.security.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSecurity getCurrentUser(){
        try {
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception ex){
            return null;
        }

    }

}
