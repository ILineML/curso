package br.com.cursospring.curso.security;

import br.com.cursospring.curso.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
//            A partir da URI do /login este método ira pegar os dados que vieram e tentar converter para minha classe
//            CredenciaisDTO
            CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());

//          Aqui o framework irá realizar a autenticação de usuário e senha, conforme foi implementado do
//          UserDetailsService
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {

//        Aqui pego email do usuário
        String username = ((UserSecurity) auth.getPrincipal()).getUsername();
//        Crio o token a partir do email
        String token = jwtUtil.generateToken(username);
//        Adiciona o token na resposta da requisição
        response.addHeader("Authorization", "Bearer " + token);

    }

}
