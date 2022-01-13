package edu.viu.presentacionescontables.pesentaciones.repository;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IPresentacionRepository extends CrudRepository<Presentacion, String> {
    Optional<Presentacion> findById(long id);

    void deleteById(long id);
}
