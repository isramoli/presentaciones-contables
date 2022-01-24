package edu.viu.presentacionescontables.pesentaciones.repository;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repositorio de presentaciones para acceder a bbdd
 */
public interface IPresentacionRepository extends CrudRepository<Presentacion, String> {
	/**
	 * Busca presentaciones por su presentación
	 *
	 * @param id Identificador de la presentación
	 * @return Devuelve la presentación
	 */
	Optional<Presentacion> findById(long id);

	/**
	 * Borra la presentación por id
	 *
	 * @param id Identificador de la presentación a borrar
	 */
	void deleteById(long id);

	/**
	 * Busca la presetación por una convocatoria
	 *
	 * @param nombreConvocatoria Nombre de la convocatoria
	 * @return Devuelve la presentación
	 */
	@Query("select p from Presentacion p where :nombreConvocatoria = p.convocatoria.nombre")
	Presentacion buscarPorConvocatoria(String nombreConvocatoria);
}
