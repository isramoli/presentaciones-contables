package edu.viu.presentacionescontables.convocatorias.service;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;

import java.util.List;
import java.util.Optional;

public interface IConvocatoriaService {

    List<Convocatoria> buscarTodasConvocatorias();

    Optional<Convocatoria> buscarPorNombre(String nombre);

    void guardar(Convocatoria convocatoria);

    void borrar(String nombre);
}
