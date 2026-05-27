package com.festivos.aplicacion.eventos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.festivos.aplicacion.servicios.EmailService;
import com.festivos.dominio.entidades.FestivoConsultadoEvent;
import com.festivos.dominio.entidades.UsuarioAutenticadoEvent;
import com.festivos.dominio.entidades.UsuarioCreadoEvent;

@Component
public class EventoListener {

    private static final Logger log = LoggerFactory.getLogger(EventoListener.class);

    private final EmailService emailService;

    public EventoListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    @EventListener
    public void onUsuarioCreado(UsuarioCreadoEvent evento) {
        log.info("EVENTO: Usuario creado: {}", evento.getNombreUsuario());

        emailService.enviarNotificacion(
            "Nuevo usuario creado",
            "Se ha creado el usuario: " + evento.getNombreUsuario()
        );
    }

    @Async
    @EventListener
    public void onUsuarioAutenticado(UsuarioAutenticadoEvent evento) {
        log.info("EVENTO: Usuario autenticado: {}", evento.getNombreUsuario());

        emailService.enviarNotificacion(
            "Usuario autenticado",
            "El usuario " + evento.getNombreUsuario() + " inició sesión el " + evento.getFecha()
        );
    }

    @Async
    @EventListener
    public void onFestivoConsultado(FestivoConsultadoEvent evento) {
        log.info("EVENTO: Festivo consultado | Fecha: {}", evento.getFecha());

        emailService.enviarNotificacion(
            "Festivo consultado",
            "Se consultó la fecha: " + evento.getFecha() + "\nResultado: " + evento.getResultado()
        );
    }
}