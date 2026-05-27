package com.festivos.core.interfaces;

import java.util.List;

import com.festivos.dominio.dtos.FestivoDto;

public interface IFestivoExternoServicio {
    List<FestivoDto> obtenerFestivosPorAnio(int anio);
}