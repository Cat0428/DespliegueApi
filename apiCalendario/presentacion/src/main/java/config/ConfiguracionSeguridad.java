package com.festivos.presentacion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.festivos.aplicacion.seguridad.FiltroSeguridad;
import com.festivos.aplicacion.seguridad.UsuarioDetalleServicio;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private FiltroSeguridad filtro;

    @Bean
    public UserDetailsService servicioUsuario() {
        return new UsuarioDetalleServicio();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        (authz) -> authz
                                .requestMatchers(
                                        "/api/usuarios/login/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/webjars/**")
                                .permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(filtro, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}