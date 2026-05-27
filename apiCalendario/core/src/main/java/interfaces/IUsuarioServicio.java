package com.festivos.core.interfaces;

import java.util.List;

import com.festivos.dominio.dtos.UsuarioLoginDto;
import com.festivos.dominio.entidades.Usuario;

public interface IUsuarioServicio {

    public UsuarioLoginDto login(String nombreUsuario, String clave);

    public List<Usuario> listar();

    public Usuario obtener(int id);

    public List<Usuario> buscar(String nombre);

    public Usuario agregar(Usuario usuario);

    public Usuario modificar(Usuario usuario);

    public boolean eliminar(int id);
}
