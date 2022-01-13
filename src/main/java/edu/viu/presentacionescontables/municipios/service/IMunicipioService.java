package edu.viu.presentacionescontables.municipios.service;

import edu.viu.presentacionescontables.municipios.entity.Municipio;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IMunicipioService {

    List<Municipio> buscarTodosMunicipios();

    Optional<Municipio> buscarPorNombre(String nombre);

    void guardar(Municipio usuario);

    void borrar(String nombre);

    List<Usuario> buscarUsuarios();
}
