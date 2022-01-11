package edu.viu.presentacionescontables.pesentaciones.repository;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import org.springframework.data.repository.CrudRepository;

public interface IPresentacionRepository extends CrudRepository<Presentacion, String> {
}
