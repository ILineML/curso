package br.com.cursospring.curso.controllers;

import br.com.cursospring.curso.dto.EmailDTO;
import br.com.cursospring.curso.security.JWTUtil;
import br.com.cursospring.curso.security.UserSecurity;
import br.com.cursospring.curso.services.AuthService;
import br.com.cursospring.curso.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @RequestMapping(value="/refresh_token", method= RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSecurity user = UserService.getCurrentUser();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/forgot", method= RequestMethod.POST)
    public ResponseEntity<Void> forgotPass(@RequestBody EmailDTO body) {
        authService.sendNewPassword(body.getEmail());
        return ResponseEntity.noContent().build();
    }

}
