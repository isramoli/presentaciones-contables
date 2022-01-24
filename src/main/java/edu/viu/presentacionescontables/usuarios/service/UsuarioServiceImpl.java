package edu.viu.presentacionescontables.usuarios.service;

import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import edu.viu.presentacionescontables.usuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * Busca todos los usuarios
	 *
	 * @return Devuelve el listado de usuarios
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarTodosUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	/**
	 * Busca todos los usuarios cuentadantes
	 *
	 * @return Devuelve el listado de usuarios cuentadantes
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> buscarUsuariosCuentadantes() {
		return usuarioRepository.buscarUsuariosCuentadantes();
	}

	/**
	 * Busca una usuario por nombre
	 *
	 * @param nombre Nombre identificador del usuario
	 * @return Devuelve el usuario
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> buscarPorNombre(String nombre) {
		return usuarioRepository.findById(nombre);
	}

	/**
	 * Guarda el usuario
	 *
	 * @param usuario Usuario a guardar
	 */
	@Override
	public void guardar(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
	}

	/**
	 * Actualiza los datos del usuario
	 *
	 * @param usuario Datos del usuario actualizado
	 */
	@Override
	public void actualizar(Usuario usuario) {
		Optional<Usuario> usuarioBBDD = usuarioRepository.findById(usuario.getNombre());
		if (usuarioBBDD.isPresent()) {
			usuarioBBDD.get().setTipoUsuario(usuario.getTipoUsuario());
			usuarioRepository.save(usuarioBBDD.get());
		}
	}

    /**
     * Borra el usuario
     *
     * @param nombre Nombre identificador del usuario
     */
	@Override
	public void borrar(String nombre) {
		usuarioRepository.deleteById(nombre);
	}
}
