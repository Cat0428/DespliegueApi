package com.festivos.dominio.entidades;

import org.springframework.context.ApplicationEvent;

public class FestivoConsultadoEvent extends ApplicationEvent {

    private final String fecha;
    private final String resultado;

    public FestivoConsultadoEvent(Object source, String fecha, String resultado) {
        super(source);
        this.fecha = fecha;
        this.resultado = resultado;
    }

    public String getFecha() { return fecha; }
    public String getResultado() { return resultado; }
}