package com.festivos.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "festivo")

public class Festivo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "dia", nullable = false)
    private Integer dia;
    @Column(name = "mes", nullable = false)
    private Integer mes;
    @Column(name = "dias_pascua")
    private Integer dias_pascua;
    @Column(name = "Id_tipo", nullable = false)
    private Integer IdTipo;

    public Festivo() {
    }

    public Festivo(Integer id, String nombre, Integer dia, Integer mes, Integer diapascua, Integer IdTipo) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.dias_pascua = dias_pascua;
        this.IdTipo = IdTipo;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getDia() {
        return dia;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getDias_pascua() {
        return dias_pascua;
    }

    public Integer getIdTipo() {
        return IdTipo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public void setDias_pascua(Integer dias_pascua) {
        this.dias_pascua = dias_pascua;
    }

    public void setIdTipo(Integer IdTipo) {
        this.IdTipo = IdTipo;
    }

    
}
