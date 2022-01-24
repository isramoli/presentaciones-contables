package edu.viu.presentacionescontables.convocatorias.repository;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repositorio de convocatorias para acceder a bbdd
 */
public interface IConvocatoriaRepository extends CrudRepository<Convocatoria, String> {
}
