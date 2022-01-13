package edu.viu.presentacionescontables.pesentaciones.service;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import edu.viu.presentacionescontables.municipios.entity.Municipio;
import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;

import java.util.List;
import java.util.Optional;

public interface IPresentacionService {

    List<Presentacion> findAll();

    Optional<Presentacion> buscarPorId(long id);

    void guardar(Presentacion usuario);

    void borrar(long id);

    List<Convocatoria> buscarConvocatorias();

    List<Municipio> buscarMunicipios();
}
