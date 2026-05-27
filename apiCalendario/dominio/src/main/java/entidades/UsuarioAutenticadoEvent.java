package com.festivos.dominio.entidades;

import org.springframework.context.ApplicationEvent;

public class UsuarioAutenticadoEvent extends ApplicationEvent {

    private final String nombreUsuario;
    private final String fecha;

    public UsuarioAutenticadoEvent(Object source, String nombreUsuario, String fecha) {
        super(source);
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public String getFecha() { return fecha; }
}