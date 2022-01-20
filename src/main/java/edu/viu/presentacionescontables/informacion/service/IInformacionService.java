package edu.viu.presentacionescontables.informacion.service;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;

import java.util.List;
import java.util.Optional;

public interface IInformacionService {

    List<Convocatoria> buscarTodasConvocatorias();

    Optional<Convocatoria> buscarPorNombre(String nombre);

    void guardar(Convocatoria Convocatoria);

    void borrar(String nombre);
}
