package com.festivos.dominio.dtos;

public class FestivoDto {
    private String fecha;
    private String descripcion;
    private String festivo;
    private String nombre;

    public FestivoDto() {
    }

    public FestivoDto(String fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFestivo() {
        return festivo;
    }

    public void setFestivo(String festivo) {
        this.festivo = festivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}