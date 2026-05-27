package com.festivos.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.festivos.core.interfaces.IUsuarioServicio;
import com.festivos.dominio.dtos.UsuarioLoginDto;
import com.festivos.dominio.entidades.Usuario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/usuarios")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioControlador {

    @Autowired
    private IUsuarioServicio servicio;

    @GetMapping(value = "/login/{nombreUsuario}/{clave}")
    public UsuarioLoginDto login(@PathVariable String nombreUsuario, @PathVariable String clave) {
        return servicio.login(nombreUsuario, clave);
    }

    @GetMapping(value = "/")
    public List<Usuario> listar() {
        return servicio.listar();
    }

    @GetMapping(value = "/obtener/{id}")
    public Usuario obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @RequestMapping(value = "/buscar/{nombre}", method = RequestMethod.GET)
    public List<Usuario> buscar(@PathVariable String nombre) {
        return servicio.buscar(nombre);
    }

    @PostMapping(value = "/")
    public Usuario crear(@RequestBody Usuario usuario) {
        return servicio.agregar(usuario);
    }

    @PutMapping(value = "/")
    public Usuario actualizar(@RequestBody Usuario usuario) {
        return servicio.modificar(usuario);
    }

    @DeleteMapping(value = "/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}
