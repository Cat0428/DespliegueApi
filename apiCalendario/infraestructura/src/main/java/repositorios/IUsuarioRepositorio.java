package com.festivos.infraestructura.repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.festivos.dominio.entidades.Usuario;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario = ?1")
     Usuario buscarPorUsuario(String usuario);


    @Query(value = "SELECT * FROM usuario WHERE usuario = ?1 AND clave = ?2", nativeQuery = true)
    Usuario loginNativo(String usuario, String clave);

    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE '%' || ?1 || '%'")
    List<Usuario> buscar(String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.usuario = ?1")
    Usuario obtener(String usuario);

    @Query("SELECT u FROM Usuario u WHERE u.usuario=?1 AND u.clave=?2")
    Usuario login(String usuario, String clave);
}
