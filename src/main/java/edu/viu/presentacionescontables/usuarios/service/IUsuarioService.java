package edu.viu.presentacionescontables.usuarios.service;

import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> buscarTodosUsuarios();

    @Transactional(readOnly = true)
    List<Usuario> buscarUsuariosCuentadantes();

    Optional<Usuario> buscarPorNombre(String nombre);

    void guardar(Usuario usuario);

    void actualizar(Usuario usuario);

    void borrar(String nombre);
}
