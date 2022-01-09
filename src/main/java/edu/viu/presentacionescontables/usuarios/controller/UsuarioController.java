package edu.viu.presentacionescontables.usuarios.controller;

import edu.viu.presentacionescontables.config.RoleEnum;
import edu.viu.presentacionescontables.login.service.LoginService;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import edu.viu.presentacionescontables.usuarios.service.IUsuarioService;
import edu.viu.presentacionescontables.util.Utils;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
public class UsuarioController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping(value = "/lista-usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        Utils.hasRole("ROLE_CUENTADANTE");
        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("usuarios", usuarios);
        return "usuarios/lista-usuarios";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/editar-usuario")
    public String crear(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("modoCreacion", true);
        model.addAttribute("tiposUsuarios", RoleEnum.obtenerTodosRoles());
        model.addAttribute("titulo", "Crear usuario");
        return "usuarios/editar-usuario";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/editar-usuario/{nombre}")
    public String editar(@PathVariable String nombre, Model model) {

        model.addAttribute("usuario", usuarioService.buscarPorNombre(nombre));
        model.addAttribute("modoCreacion", false);
        model.addAttribute("tiposUsuarios", RoleEnum.obtenerTodosRoles());
        model.addAttribute("titulo", "Editar usuario");

        return "usuarios/editar-usuario";
    }

    @Secured("ROLE_ADMIN")
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

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/eliminar/{nombre}")
    public String eliminar(@PathVariable String nombre, RedirectAttributes flash) {

        usuarioService.borrar(nombre);
        flash.addFlashAttribute("success", "Usuario eliminado con correctamente!");

        return "redirect:/usuarios/lista-usuarios";
    }
}
