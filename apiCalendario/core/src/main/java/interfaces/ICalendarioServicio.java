package com.festivos.core.interfaces;

import java.util.List;

import com.festivos.dominio.entidades.DiaCalendario;

public interface ICalendarioServicio {
    boolean generarCalendario(int anio);
    List<DiaCalendario> obtenerCalendario(int anio);
}