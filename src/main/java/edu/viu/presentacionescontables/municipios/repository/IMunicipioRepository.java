package edu.viu.presentacionescontables.municipios.repository;

import edu.viu.presentacionescontables.municipios.entity.Municipio;
import org.springframework.data.repository.CrudRepository;

public interface IMunicipioRepository extends CrudRepository<Municipio, String> {
}
