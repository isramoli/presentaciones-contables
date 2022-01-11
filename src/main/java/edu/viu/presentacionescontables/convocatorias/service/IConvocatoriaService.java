package edu.viu.presentacionescontables.convocatorias.service;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IConvocatoriaService {

    List<Convocatoria> buscarTodasConvocatorias();

    Optional<Convocatoria> buscarPorNombre(String nombre);

    void guardar(Convocatoria Convocatoria);

    void borrar(String nombre);
}
