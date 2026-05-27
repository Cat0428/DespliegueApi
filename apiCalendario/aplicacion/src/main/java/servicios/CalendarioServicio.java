package com.festivos.aplicacion.servicios;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.festivos.core.interfaces.ICalendarioServicio;
import com.festivos.core.interfaces.IFestivoExternoServicio;
import com.festivos.dominio.dtos.FestivoDto;
import com.festivos.dominio.entidades.DiaCalendario;
import com.festivos.infraestructura.repositorios.IDiaCalendarioRepositorio;

@Service
public class CalendarioServicio implements ICalendarioServicio {

    @Autowired
    private IFestivoExternoServicio festivoExternoServicio;

    @Autowired
    private IDiaCalendarioRepositorio repositorio;

    @Override
    @Transactional
    public boolean generarCalendario(int anio) {
        try {
            repositorio.deleteByAnio(anio);

            List<FestivoDto> festivos = festivoExternoServicio.obtenerFestivosPorAnio(anio);

            List<String> fechasFestivos = new ArrayList<>();
            for (FestivoDto f : festivos) {
                fechasFestivos.add(f.getFecha());
            }

            List<DiaCalendario> dias = new ArrayList<>();
            LocalDate fecha = LocalDate.of(anio, 1, 1);
            LocalDate fin = LocalDate.of(anio, 12, 31);

            while (!fecha.isAfter(fin)) {
                String fechaStr = fecha.toString();
                String tipo;
                String descripcion = obtenerNombreDia(fecha.getDayOfWeek());

                if (fechasFestivos.contains(fechaStr)) {
                    tipo = "FESTIVO";
                } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY ||
                           fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    tipo = "FIN_DE_SEMANA";
                } else {
                    tipo = "LABORAL";
                }

                dias.add(new DiaCalendario(fechaStr, tipo, anio, descripcion));
                fecha = fecha.plusDays(1);
            }

            repositorio.saveAll(dias);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error generando calendario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<DiaCalendario> obtenerCalendario(int anio) {
        return repositorio.findByAnio(anio);
    }

    private String obtenerNombreDia(DayOfWeek dia) {
        switch (dia) {
            case MONDAY:    return "Lunes";
            case TUESDAY:   return "Martes";
            case WEDNESDAY: return "Miércoles";
            case THURSDAY:  return "Jueves";
            case FRIDAY:    return "Viernes";
            case SATURDAY:  return "Sábado";
            case SUNDAY:    return "Domingo";
            default:        return "";
        }
    }
}