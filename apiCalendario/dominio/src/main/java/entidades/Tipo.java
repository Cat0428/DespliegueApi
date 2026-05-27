package com.festivos.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo", length = 100, nullable = false)
    private String nombreTipo;

    public Tipo() {
    }

    public Tipo(Integer id, String nombreTipo) {
        this.id = id;
        this.nombreTipo = nombreTipo;
    }

    public Integer getId() {
        return id;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}