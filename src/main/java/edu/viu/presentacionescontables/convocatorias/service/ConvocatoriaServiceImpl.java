package edu.viu.presentacionescontables.convocatorias.service;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import edu.viu.presentacionescontables.convocatorias.repository.IConvocatoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConvocatoriaServiceImpl implements IConvocatoriaService {

	@Autowired
	private IConvocatoriaRepository convocatoriaRepository;

	/**
	 * Busca todas las convocatorias
	 *
	 * @return Devuelve el listado de convocatorias
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Convocatoria> buscarTodasConvocatorias() {
		return (List<Convocatoria>) convocatoriaRepository.findAll();
	}

	/**
	 * Busca una convocatoria por nombre
	 *
	 * @param nombre Nombre identificador de la convocatoria
	 * @return Devuelve la convocatoria
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Convocatoria> buscarPorNombre(String nombre) {
		return convocatoriaRepository.findById(nombre);
	}

	/**
	 * Guarda la convocatoria
	 *
	 * @param convocatoria Convocatoria a guardar
	 */
	@Override
	public void guardar(Convocatoria convocatoria) {
		convocatoriaRepository.save(convocatoria);
	}

	/**
	 * Borra la convocatoria
	 *
	 * @param nombre Nombre identificador de la convocatoria
	 */
	@Override
	public void borrar(String nombre) {
		convocatoriaRepository.deleteById(nombre);
	}


}
