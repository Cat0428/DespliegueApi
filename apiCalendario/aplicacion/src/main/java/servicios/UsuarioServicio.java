package com.festivos.aplicacion.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.festivos.aplicacion.seguridad.SeguridadServicio;
import com.festivos.core.interfaces.IUsuarioServicio;
import com.festivos.dominio.dtos.UsuarioLoginDto;
import com.festivos.dominio.entidades.Usuario;
import com.festivos.dominio.entidades.UsuarioAutenticadoEvent;
import com.festivos.dominio.entidades.UsuarioCreadoEvent;
import com.festivos.infraestructura.repositorios.IUsuarioRepositorio;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private SeguridadServicio servicioSeguridad;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public UsuarioLoginDto login(String nombreUsuario, String clave) {
        Usuario usuarioObtenido = repositorio.login(nombreUsuario, clave);
        UsuarioLoginDto userLoginResponseDto = new UsuarioLoginDto(usuarioObtenido);
        if (usuarioObtenido != null) {
            userLoginResponseDto.setToken(servicioSeguridad.generarToken(nombreUsuario));
            eventPublisher.publishEvent(new UsuarioAutenticadoEvent(
                this, nombreUsuario, LocalDateTime.now().toString()
            ));
        }
        return userLoginResponseDto;
    }

    @Override
    public List<Usuario> listar() {
        return repositorio.findAll();
    }

    @Override
    public Usuario obtener(int id) {
        var usuario = repositorio.findById(id);
        return usuario.isEmpty() ? null : usuario.get();
    }

    @Override
    public List<Usuario> buscar(String nombre) {
        return repositorio.buscar(nombre);
    }

    @Override
    public Usuario agregar(Usuario usuario) {
        usuario.setId(0);
        Usuario usuarioGuardado = repositorio.save(usuario);
        eventPublisher.publishEvent(new UsuarioCreadoEvent(
            this, usuarioGuardado.getUsuario()
        ));
        return usuarioGuardado;
    }

    @Override
    public Usuario modificar(Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = repositorio.findById(usuario.getId());
        if (!usuarioEncontrado.isEmpty()) {
            return repositorio.save(usuario);
        } else {
            return null;
        }
    }

    @Override
    public boolean eliminar(int id) {
        try {
            var usuarioEncontrado = repositorio.findById(id);
            if (usuarioEncontrado.isEmpty()) {
                return false;
            }
            repositorio.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}