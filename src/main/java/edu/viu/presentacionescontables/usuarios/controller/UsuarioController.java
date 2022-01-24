package edu.viu.presentacionescontables.usuarios.controller;

import edu.viu.presentacionescontables.config.RoleEnum;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import edu.viu.presentacionescontables.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador para ventanas sobre usuarios
 */
@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * Devuelve la pantalla de listado de usuarios con datos cargados
	 *
	 * @param model model de spring
	 * @return Pantalla a cargar
	 */
	@GetMapping(value = "/lista-usuarios")
	public String listarUsuarios(Model model) {
		List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarios);
		return "usuarios/lista-usuarios";
	}

	/**
	 * Devuelve la pantalla de crear de usuario
	 *
	 * @param model model de spring
	 * @return Pantalla a cargar
	 */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
	@GetMapping(value = "/editar-usuario")
	public String crear(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("modoCreacion", true);
		model.addAttribute("tiposUsuarios", RoleEnum.obtenerTodosRoles());
		model.addAttribute("titulo", "Crear usuario");
		return "usuarios/editar-usuario";
	}

	/**
	 * Devuelve la pantalla de editar de usuario
	 *
	 * @param nombre Nombre identificador del usuario
	 * @param model  model de spring
	 * @return Pantalla de edición de usuario
	 */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
	@GetMapping("/editar-usuario/{nombre}")
	public String editar(@PathVariable String nombre, Model model) {

		model.addAttribute("usuario", usuarioService.buscarPorNombre(nombre));
		model.addAttribute("modoCreacion", false);
		model.addAttribute("tiposUsuarios", RoleEnum.obtenerTodosRoles());
		model.addAttribute("titulo", "Editar usuario");

		return "usuarios/editar-usuario";
	}

	/**
	 * Guarda el usuario
	 *
	 * @param usuario      Usuario a guardar
	 * @param result       Resultado de la operación
	 * @param model        model de spring
	 * @param modoCreacion Booleano para saber si es una edición o un alta
	 * @param flash        Atrubitos de la redirección de spring
	 * @param status       Estado de la sesión de spring
	 * @return Devuelve la pantalla de listado
	 */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
	@PostMapping("/editar-usuario")
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, boolean modoCreacion,
						  RedirectAttributes flash, SessionStatus status) {
		String respuesta;
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de usuario");
			respuesta = "/usuarios/editar-usuario";
		} else {
			String mensajeFlash = modoCreacion ? "Usuario creado con éxito!" : "Usuario editado con éxito!";

			if (modoCreacion) {
				usuarioService.guardar(usuario);
			} else {
				usuarioService.actualizar(usuario);
			}

			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			respuesta = "redirect:/usuarios/lista-usuarios";
		}

		return respuesta;
	}

	/**
	 * Punto entrada para eliminación de usuarios
	 *
	 * @param nombre Id del usuario
	 * @param flash  Atrubitos de la redirección de spring
	 * @return Devuelve la pantalla de listado
	 */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
	@GetMapping(value = "/eliminar/{nombre}")
	public String eliminar(@PathVariable String nombre, RedirectAttributes flash) {

		usuarioService.borrar(nombre);
		flash.addFlashAttribute("success", "Usuario eliminado con correctamente!");

		return "redirect:/usuarios/lista-usuarios";
	}
}
