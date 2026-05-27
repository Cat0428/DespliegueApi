package com.festivos.infraestructura.repositorios; 

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.festivos.dominio.entidades.Festivo;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {
}