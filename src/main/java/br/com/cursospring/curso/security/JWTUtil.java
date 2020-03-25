package br.com.cursospring.curso.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String userName){
//        Após o login com sucesso, o Token é gerado por aqui
        return Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }

    public Boolean isValidToken(String token){
        Claims claims = getClains(token);

        if(claims != null){
            String username = claims.getSubject();
            Date expirationToken = claims.getExpiration();
            Date currentDate = new Date(System.currentTimeMillis());

            if(username!= null && expirationToken != null & currentDate.before(expirationToken)){
                return true;
            }

        }

        return false;

    }

    public String getUsername(String token) {
        Claims claims = getClains(token);

        if (claims != null) {
            return claims.getSubject();
        }

        return null;
    }

    private Claims getClains(String token){
        try{
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception ex){
            return null;
        }

    }

}
