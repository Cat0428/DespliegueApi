package com.festivos.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dia_calendario")
public class DiaCalendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "anio")
    private int anio;

    @Column(name = "descripcion")
    private String descripcion;

    public DiaCalendario() {}

    public DiaCalendario(String fecha, String tipo, int anio, String descripcion) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.anio = anio;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}