package edu.viu.presentacionescontables.pesentaciones.repository;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IPresentacionRepository extends CrudRepository<Presentacion, String> {
    Optional<Presentacion> findById(long id);

    void deleteById(long id);

    @Query("select p from Presentacion p where :nombreConvocatoria = p.convocatoria.nombre")
    Presentacion buscarPorConvocatoria(String nombreConvocatoria);
}
