package com.festivos.dominio.entidades;

import org.springframework.context.ApplicationEvent;

public class UsuarioCreadoEvent extends ApplicationEvent {

    private final String nombreUsuario;

    public UsuarioCreadoEvent(Object source, String nombreUsuario) {
        super(source);
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() { return nombreUsuario; }
}