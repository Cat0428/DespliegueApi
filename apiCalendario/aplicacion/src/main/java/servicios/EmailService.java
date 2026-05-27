package com.festivos.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.admin}")
    private String adminEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarNotificacion(String asunto, String cuerpo) {
    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setFrom("cristian.dev0428@gmail.com");  // ← agregar esta línea
    mensaje.setTo(adminEmail);
    mensaje.setSubject(asunto);
    mensaje.setText(cuerpo);
    mailSender.send(mensaje);
    }
}