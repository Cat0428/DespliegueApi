package com.festivos.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festivos.dominio.entidades.DiaCalendario;

@Repository
public interface IDiaCalendarioRepositorio extends JpaRepository<DiaCalendario, Integer> {

    void deleteByAnio(int anio);

    List<DiaCalendario> findByAnio(int anio);
}