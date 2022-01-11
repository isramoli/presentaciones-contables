package edu.viu.presentacionescontables.pesentaciones.controller;

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
@RequestMapping("/municipios")
@SessionAttributes("municipios")
public class Presentacion {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IPresentacionService municipioService;

    @PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_CUENTADANTE.getRole())")
    @GetMapping(value = "/lista-municipios")
    public String listarmunicipios(Model model) {
        List<edu.viu.presentacionescontables.pesentaciones.entity.Presentacion> presentaciones = municipioService.findAll();

        model.addAttribute("titulo", "Listado de municipios");
        model.addAttribute("municipios", presentaciones);
        return "municipios/lista-municipios";
    }

    @PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
    @GetMapping(value = "/editar-municipio")
    public String crear(Model model) {
        edu.viu.presentacionescontables.pesentaciones.entity.Presentacion presentacion = new edu.viu.presentacionescontables.pesentaciones.entity.Presentacion();
        model.addAttribute("municipio", presentacion);
        model.addAttribute("modoCreacion", true);
        model.addAttribute("listaCuentadantes", municipioService.buscarUsuarios());
        model.addAttribute("titulo", "Crear municipio");
        return "municipios/editar-municipio";
    }

    @PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
    @GetMapping("/editar-municipio/{nombre}")
    public String editar(@PathVariable String nombre, Model model) {

        model.addAttribute("municipio", municipioService.buscarPorNombre(nombre));
        model.addAttribute("modoCreacion", false);
        model.addAttribute("listaCuentadantes", municipioService.buscarUsuarios());
        model.addAttribute("titulo", "Editar municipio");

        return "municipios/editar-municipio";
    }

    @PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
    @PostMapping("/editar-municipio")
    public String guardar(@Valid edu.viu.presentacionescontables.pesentaciones.entity.Presentacion presentacion, BindingResult result, Model model, boolean modoCreacion,
                          RedirectAttributes flash, SessionStatus status) {
        String respuesta;
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de usuario");
            respuesta = "municipios/editar-municipio";
        } else {
            String mensajeFlash = modoCreacion ? "Municipio creado con éxito!" : "Municipio editado con éxito!";

            municipioService.guardar(presentacion);

            status.setComplete();
            flash.addFlashAttribute("success", mensajeFlash);
            respuesta = "redirect:/municipios/lista-municipios";
        }

        return respuesta;
    }

    @PreAuthorize("hasRole(T(edu.viu.presentacionescontables.config.RoleEnum).ROLE_ADMIN.getRole())")
    @GetMapping(value = "/eliminar/{nombre}")
    public String eliminar(@PathVariable String nombre, RedirectAttributes flash) {

        municipioService.borrar(nombre);
        flash.addFlashAttribute("success", "Usuario eliminado con correctamente!");

        return "redirect:/municipios/lista-municipios";
    }
}
