package edu.viu.presentacionescontables.pesentaciones.controller;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import edu.viu.presentacionescontables.pesentaciones.service.IPresentacionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

@Controller
@RequestMapping("/presentaciones")
@SessionAttributes("presentaciones")
public class PresentacionController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IPresentacionService presentacionService;

    @PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_CUENTADANTE.getRole())")
    @GetMapping(value = "/lista-presentaciones")
    public String listarpresentaciones(Model model) {
        List<Presentacion> presentaciones = presentacionService.findAll();

        model.addAttribute("titulo", "Listado de presentaciones");
        model.addAttribute("presentaciones", presentaciones);
        return "presentaciones/lista-presentaciones";
    }

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

    @PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
    @GetMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable long id, RedirectAttributes flash) {

        presentacionService.borrar(id);
        flash.addFlashAttribute("success", "Presentación eliminado con correctamente!");

        return "redirect:/presentaciones/lista-presentaciones";
    }
}
