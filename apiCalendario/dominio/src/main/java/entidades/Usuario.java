package com.festivos.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_usuario")
    @SequenceGenerator(name = "secuencia_usuario", sequenceName = "secuencia_usuario", allocationSize = 1)
    private Integer id;

    @Column(name = "usuario", length = 100, unique = true)
    private String usuario;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "clave")
    private String clave;

    @Column(name = "roles")
    private String roles;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "foto")
    private byte[] foto;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String nombre) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }

   public Boolean getActivo() { return activo; }
   public void setActivo(Boolean activo) { this.activo = activo; }

   public byte[] getFoto() { return foto; }
   public void setFoto(byte[] foto) { this.foto = foto; }
}
