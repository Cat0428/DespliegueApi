package com.festivos.presentacion.controladores;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festivos.core.interfaces.ICalendarioServicio;
import com.festivos.core.interfaces.IFestivoExternoServicio;
import com.festivos.core.interfaces.IFestivoServicio;
import com.festivos.dominio.dtos.FestivoDto;
import com.festivos.dominio.entidades.DiaCalendario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/festivos")
@SecurityRequirement(name = "bearerAuth")
public class FestivoControlador {

    private final IFestivoServicio servicio;

    @Autowired
    private IFestivoExternoServicio festivoExternoServicio;

    @Autowired
    private ICalendarioServicio calendarioServicio;

    public FestivoControlador(IFestivoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/verificar/{ano}/{mes}/{dia}")
    public String verificarFestivo(
        @PathVariable int ano,
        @PathVariable int mes,
        @PathVariable int dia) {

        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes - 1, dia);
        Date fecha = cal.getTime();

        return servicio.esFestivo(fecha);
    }

    @GetMapping("/externos/{anio}")
    public List<FestivoDto> obtenerFestivosExternos(@PathVariable int anio) {
        return festivoExternoServicio.obtenerFestivosPorAnio(anio);
    }

    @GetMapping("/generar/{anio}")
    public boolean generarCalendario(@PathVariable int anio) {
        return calendarioServicio.generarCalendario(anio);
    }

    @GetMapping("/calendario/{anio}")
    public List<DiaCalendario> obtenerCalendario(@PathVariable int anio) {
        return calendarioServicio.obtenerCalendario(anio);
    }
}