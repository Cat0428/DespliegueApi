package com.festivos.infraestructura.integracion;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.festivos.core.interfaces.IFestivoExternoServicio;
import com.festivos.dominio.dtos.FestivoDto;

@Service
public class FestivoClienteServicio implements IFestivoExternoServicio {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${festivos.externo.url}")
    private String urlBase;

    @Override
    public List<FestivoDto> obtenerFestivosPorAnio(int anio) {
        String url = urlBase + "/" + anio;
        FestivoDto[] festivos = restTemplate.getForObject(url, FestivoDto[].class);
        return festivos != null ? Arrays.asList(festivos) : List.of();
    }
}