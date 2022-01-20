package edu.viu.presentacionescontables.informacion.controller;

import edu.viu.presentacionescontables.config.RoleEnum;
import edu.viu.presentacionescontables.convocatorias.enums.TipoDocumentacionEnum;
import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import edu.viu.presentacionescontables.informacion.service.IInformacionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

@Controller
@RequestMapping("/informacion")
public class InformacionController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IInformacionService informacionService;

    @GetMapping(value = "/lista")
    public String listarConvocatorias(Model model) {

        List<Convocatoria> convocatorias = informacionService.buscarTodasConvocatorias();
        model.addAttribute("titulo", "Listado de informacion");
        model.addAttribute("convocatorias", convocatorias);

        return "informacion/lista-informacion";
    }

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/editar-convocatoria/{nombre}")
    public String editar(@PathVariable String nombre, Model model) {

        model.addAttribute("convocatoria", informacionService.buscarPorNombre(nombre));
        model.addAttribute("modoCreacion", false);
        model.addAttribute("tiposConvocatorias", RoleEnum.obtenerTodosRoles());
        model.addAttribute("titulo", "Editar convocatoria");

        return "convocatorias/editar-convocatoria";
    }

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
            informacionService.guardar(convocatoria);
            status.setComplete();
            flash.addFlashAttribute("success", mensajeFlash);
            respuesta = "redirect:/convocatorias/lista-convocatorias";
        }

        return respuesta;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/eliminar/{nombre}")
    public String eliminar(@PathVariable String nombre, RedirectAttributes flash) {

        informacionService.borrar(nombre);
        flash.addFlashAttribute("success", "Convocatoria eliminada con correctamente!");

        return "redirect:/convocatorias/lista-convocatorias";
    }
}
