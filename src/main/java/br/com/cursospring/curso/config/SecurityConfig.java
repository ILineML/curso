package br.com.cursospring.curso.config;

import br.com.cursospring.curso.security.JWTAuthenticationFilter;
import br.com.cursospring.curso.security.JWTUtil;
import br.com.cursospring.curso.services.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] PUBLIC_MATCHERS = {
            "h2-console/**",
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
            "/produtos/**",
            "/categorias/**",
            "/clientes/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if(Arrays.asList(environment.getActiveProfiles()).contains("teste")){
            http.headers().frameOptions().disable();
        }
//HttpMethod.GET é utilizado para permitir aquelas URIs que apenas tiverem o verbo GET
        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().anyRequest().authenticated();

//        Adiciona o filtro para autenticação
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
