package edu.viu.presentacionescontables.pesentaciones.service;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IPresentacionService {

    List<Presentacion> findAll();

    Optional<Presentacion> buscarPorNombre(String nombre);

    void guardar(Presentacion usuario);

    void borrar(String nombre);

    List<Usuario> buscarUsuarios();
}
