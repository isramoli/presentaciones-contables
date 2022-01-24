package edu.viu.presentacionescontables.convocatorias.controller;

import edu.viu.presentacionescontables.config.RoleEnum;
import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import edu.viu.presentacionescontables.convocatorias.enums.TipoDocumentacionEnum;
import edu.viu.presentacionescontables.convocatorias.service.IConvocatoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador para ventanas sobre convocatorias
 */
@Controller
@RequestMapping("/convocatorias")
public class ConvocatoriaController {

	@Autowired
	private IConvocatoriaService convocatoriaServiceImpl;

	/**
	 * Devuelve la pantalla de listado de convocatorias con datos cargados
	 *
	 * @param model model de spring
	 * @return Pantalla a cargar
	 */
	@GetMapping(value = "/lista-convocatorias")
	public String listarConvocatorias(Model model) {

		List<Convocatoria> convocatorias = convocatoriaServiceImpl.buscarTodasConvocatorias();
		model.addAttribute("titulo", "Listado de convocatorias");
		model.addAttribute("convocatorias", convocatorias);

		return "convocatorias/lista-convocatorias";
	}

	/**
	 * Devuelve la pantalla de crear de convocatoria
	 *
	 * @param model model de spring
	 * @return Pantalla a cargar
	 */
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/editar-convocatoria")
	public String crear(Model model) {

		Convocatoria Convocatoria = new Convocatoria();
		model.addAttribute("convocatoria", Convocatoria);
		model.addAttribute("modoCreacion", true);
		model.addAttribute("tiposConvocatorias", TipoDocumentacionEnum.obtenerTodosTipos());
		model.addAttribute("titulo", "Crear convocatoria");

		return "convocatorias/editar-convocatoria";
	}

	/**
	 * Devuelve la pantalla de editar de convocatoria
	 *
	 * @param nombre Nombre identificador de la convocatoria
	 * @param model  model de spring
	 * @return Pantalla de edición de convocatorias
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editar-convocatoria/{nombre}")
	public String editar(@PathVariable String nombre, Model model) {

		model.addAttribute("convocatoria", convocatoriaServiceImpl.buscarPorNombre(nombre));
		model.addAttribute("modoCreacion", false);
		model.addAttribute("tiposConvocatorias", RoleEnum.obtenerTodosRoles());
		model.addAttribute("titulo", "Editar convocatoria");

		return "convocatorias/editar-convocatoria";
	}

	/**
	 * Guarda la convocatoria
	 *
	 * @param convocatoria Convocatoria a guardar
	 * @param result       Resultado de la operación
	 * @param model        model de spring
	 * @param modoCreacion Booleano para saber si es una edición o un alta
	 * @param flash        Atrubitos de la redirección de spring
	 * @param status       Estado de la sesión de spring
	 * @return Devuelve la pantalla de listado
	 */
	@Secured("ROLE_ADMIN")
	@PostMapping("/editar-convocatoria")
	public String guardar(@Valid Convocatoria convocatoria, BindingResult result, Model model, boolean modoCreacion,
						  RedirectAttributes flash, SessionStatus status) {
		String respuesta;
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de convocatoria");
			respuesta = "convocatorias/editar-convocatoria";
		} else {
			String mensajeFlash = modoCreacion ? "Convocatoria creada con éxito!" : "Convocatoria editada con éxito!";
			convocatoriaServiceImpl.guardar(convocatoria);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			respuesta = "redirect:/convocatorias/lista-convocatorias";
		}

		return respuesta;
	}

	/**
	 * Punto entrada para eliminación de convocatoria
	 *
	 * @param nombre Id de la convocatoria
	 * @param flash  Atrubitos de la redirección de spring
	 * @return Devuelve la pantalla de listado
	 */
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/eliminar/{nombre}")
	public String eliminar(@PathVariable String nombre, RedirectAttributes flash) {

		convocatoriaServiceImpl.borrar(nombre);
		flash.addFlashAttribute("success", "Convocatoria eliminada con correctamente!");

		return "redirect:/convocatorias/lista-convocatorias";
	}
}
