package edu.viu.presentacionescontables.municipios.repository;

import edu.viu.presentacionescontables.municipios.entity.Municipio;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio de municipios para acceder a bbdd
 */
public interface IMunicipioRepository extends CrudRepository<Municipio, String> {
}
