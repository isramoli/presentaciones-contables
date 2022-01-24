package edu.viu.presentacionescontables.pesentaciones.controller;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import edu.viu.presentacionescontables.pesentaciones.service.IPresentacionService;
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
 * Controlador para ventanas sobre presentaciones
 */
@Controller
@RequestMapping("/presentaciones")
@SessionAttributes("presentaciones")
public class PresentacionController {

	@Autowired
	private IPresentacionService presentacionService;

    /**
     * Devuelve la pantalla de listado de las presentaciones con datos cargados
     *
     * @param model model de spring
     * @return Pantalla a cargar
     */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_CUENTADANTE.getRole())")
	@GetMapping(value = "/lista-presentaciones")
	public String listarpresentaciones(Model model) {
		List<Presentacion> presentaciones = presentacionService.findAll();

		model.addAttribute("titulo", "Listado de presentaciones");
		model.addAttribute("presentaciones", presentaciones);
		return "presentaciones/lista-presentaciones";
	}

    /**
     * Devuelve la pantalla de crear presentaciones
     *
     * @param model model de spring
     * @return Pantalla a cargar
     */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
	@GetMapping(value = "/editar-presentacion")
	public String crear(Model model) {
		Presentacion presentacion = new Presentacion();
		model.addAttribute("presentacion", presentacion);
		model.addAttribute("modoCreacion", true);
		model.addAttribute("listaConvocatorias", presentacionService.buscarConvocatorias());
		model.addAttribute("listaMunicipios", presentacionService.buscarMunicipios());
		model.addAttribute("titulo", "Crear presentacion");
		return "presentaciones/editar-presentacion";
	}

    /**
     * Devuelve la pantalla de editar de presentaciones
     *
     * @param id Identificador de la presentacion
     * @param model  model de spring
     * @return Pantalla de edición de presentaciones
     */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
	@GetMapping("/editar-presentacion/{id}")
	public String editar(@PathVariable long id, Model model) {

		model.addAttribute("presentacion", presentacionService.buscarPorId(id));
		model.addAttribute("modoCreacion", false);
		model.addAttribute("listaConvocatorias", presentacionService.buscarConvocatorias());
		model.addAttribute("listaMunicipios", presentacionService.buscarMunicipios());
		model.addAttribute("titulo", "Editar presentacion");

		return "presentaciones/editar-presentacion";
	}

    /**
     * Guarda la presentacion
     *
     * @param presentacion Convocatoria a guardar
     * @param result       Resultado de la operación
     * @param model        model de spring
     * @param modoCreacion Booleano para saber si es una edición o un alta
     * @param flash        Atrubitos de la redirección de spring
     * @param status       Estado de la sesión de spring
     * @return Devuelve la pantalla de listado
     */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
	@PostMapping("/editar-presentacion")
	public String guardar(@Valid Presentacion presentacion, BindingResult result, Model model, boolean modoCreacion,
						  RedirectAttributes flash, SessionStatus status) {
		String respuesta;
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de usuario");
			respuesta = "presentaciones/editar-presentacion";
		} else {
			String mensajeFlash = modoCreacion ? "Presentación creada con éxito!" : "Presentación editada con éxito!";

			presentacionService.guardar(presentacion);

			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			respuesta = "redirect:/presentaciones/lista-presentaciones";
		}

		return respuesta;
	}

    /**
     * Punto entrada para eliminación de una presentación
     *
     * @param id Id dela presentacion
     * @param flash  Atrubitos de la redirección de spring
     * @return Devuelve la pantalla de listado
     */
	@PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
	@GetMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable long id, RedirectAttributes flash) {

		presentacionService.borrar(id);
		flash.addFlashAttribute("success", "Presentación eliminado con correctamente!");

		return "redirect:/presentaciones/lista-presentaciones";
	}
}
