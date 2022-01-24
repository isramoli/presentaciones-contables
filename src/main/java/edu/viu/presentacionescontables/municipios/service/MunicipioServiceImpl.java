package edu.viu.presentacionescontables.municipios.service;

import edu.viu.presentacionescontables.municipios.entity.Municipio;
import edu.viu.presentacionescontables.municipios.repository.IMunicipioRepository;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import edu.viu.presentacionescontables.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioServiceImpl implements IMunicipioService {

	@Autowired
	private IMunicipioRepository municipioRepository;

	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * Busca todos los municipios
	 *
	 * @return Devuelve el listado de municipios
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Municipio> buscarTodosMunicipios() {
		return (List<Municipio>) municipioRepository.findAll();
	}

	/**
	 * Busca un municipio por nombre
	 *
	 * @param nombre Nombre identificador del municipio
	 * @return Devuelve el municipio
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Municipio> buscarPorNombre(String nombre) {
		return municipioRepository.findById(nombre);
	}

	/**
	 * Guarda el municipio
	 *
	 * @param municipio Municipio a guardar
	 */
	@Override
	public void guardar(Municipio municipio) {
		municipioRepository.save(municipio);
	}

	/**
	 * Borra el municipio
	 *
	 * @param nombre Nombre identificador del municipio
	 */
	@Override
	public void borrar(String nombre) {
		municipioRepository.deleteById(nombre);
	}

	/**
	 * Busca el listado de usuarios cuentadantes
	 *
	 * @return Devuelve el listado de usuarios cuentadantes
	 */
	@Override
	public List<Usuario> buscarUsuariosCuentadantes() {
		return usuarioService.buscarUsuariosCuentadantes();
	}
}
