package com.festivos.aplicacion.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.festivos.core.interfaces.IFestivoServicio;
import com.festivos.dominio.entidades.Festivo;
import com.festivos.dominio.entidades.FestivoConsultadoEvent;
import com.festivos.infraestructura.repositorios.IFestivoRepositorio;

@Service
public class FestivoServicio implements IFestivoServicio {

    private final IFestivoRepositorio repositorio;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public FestivoServicio(IFestivoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public String esFestivo(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH) + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        List<Festivo> festivos = repositorio.findAll();
        Date pascua = getDomingoPascua(año);

        for (Festivo f : festivos) {
            Date fechaFestivo = calcularFecha(f, año, pascua);
            Calendar calFestivo = Calendar.getInstance();
            calFestivo.setTime(fechaFestivo);

            if (calFestivo.get(Calendar.MONTH) + 1 == mes &&
                calFestivo.get(Calendar.DAY_OF_MONTH) == dia) {
                String resultado = "Es Festivo: " + f.getNombre();
                eventPublisher.publishEvent(new FestivoConsultadoEvent(
                    this, dia + "/" + mes + "/" + año, resultado
                ));
                return resultado;
            }
        }

        String resultado = "No es festivo";
        eventPublisher.publishEvent(new FestivoConsultadoEvent(
            this, dia + "/" + mes + "/" + año, resultado
        ));
        return resultado;
    }

    private Date calcularFecha(Festivo festivo, int año, Date pascua) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        if (festivo.getIdTipo() == 1) {
            cal.set(año, festivo.getMes() - 1, festivo.getDia());
        } else if (festivo.getIdTipo() == 2) {
            cal.set(año, festivo.getMes() - 1, festivo.getDia());
            moverAlLunes(cal);
        } else if (festivo.getIdTipo() == 3) {
            cal.setTime(pascua);
            if (festivo.getDias_pascua() != null) {
                cal.add(Calendar.DATE, festivo.getDias_pascua());
            }
        } else if (festivo.getIdTipo() == 4) {
            cal.setTime(pascua);
            if (festivo.getDias_pascua() != null) {
                cal.add(Calendar.DATE, festivo.getDias_pascua());
            }
            moverAlLunes(cal);
        }

        return cal.getTime();
    }

    private void moverAlLunes(Calendar cal) {
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
        if (diaSemana != Calendar.MONDAY) {
            if (diaSemana == Calendar.SUNDAY) {
                cal.add(Calendar.DATE, 1);
            } else {
                cal.add(Calendar.DATE, (diaSemana == Calendar.SUNDAY) ? 1 : (8 - diaSemana + 2) % 7);
            }
            while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                cal.add(Calendar.DATE, 1);
            }
        }
    }

    private Date getDomingoPascua(int año) {
        int a = año % 19;
        int b = año / 100;
        int c = año % 100;
        int d = b / 4;
        int e = b % 4;
        int g = (8 * b + 13) / 25;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 19 * l) / 433;
        int n = (h + l - 7 * m + 90) / 25;
        int p = (h + l - 7 * m + 114) % 31;

        Calendar calendar = Calendar.getInstance();
        calendar.set(año, n - 1, p + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}