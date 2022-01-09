package com.bolsadeideas.springboot.app.usuarios.service;

import com.bolsadeideas.springboot.app.usuarios.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> buscarPorNombre(String nombre);

    void guardar(Usuario usuario);

    void actualizar(Usuario usuario);

    void borrar(String nombre);
}
