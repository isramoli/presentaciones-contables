package com.bolsadeideas.springboot.app.usuarios.controller;

import com.bolsadeideas.springboot.app.login.service.LoginService;
import com.bolsadeideas.springboot.app.usuarios.entity.Usuario;
import com.bolsadeideas.springboot.app.usuarios.service.IUsuarioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller("/usuarios")
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
public class UsuarioController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping(value = "/lista-usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        hasRole("asd");
        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("usuarios", usuarios);
        return "usuarios/lista-usuarios";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/ver/{nombre}")
    public String ver(@PathVariable String nombre, Model model, RedirectAttributes flash) {
        String respuesta;
        Optional<Usuario> usuario = usuarioService.buscarPorNombre(nombre);

        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            model.addAttribute("titulo", "Detalle del usuario: " + usuario.get().getNombre());
            respuesta = "usuarios/ver-usuario";
        } else {
            flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
            respuesta = "redirect:/lista-usuarios";
        }

        return respuesta;
    }


    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/editar-usuario")
    public String crear(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("modoCreacion", true);
        model.addAttribute("tiposUsuarios", LoginService.RoleEnum.obtenerTodosRoles());
        model.addAttribute("titulo", "Crear usuario");
        return "usuarios/editar-usuario";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/editar-usuario/{nombre}")
    public String editar(@PathVariable String nombre, Model model, RedirectAttributes flash) {

        model.addAttribute("usuario", usuarioService.buscarPorNombre(nombre));
        model.addAttribute("modoCreacion", false);
        model.addAttribute("tiposUsuarios", LoginService.RoleEnum.obtenerTodosRoles());
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

    private boolean hasRole(String role) {
        boolean respuesta;
        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null) {
            respuesta = false;
        } else {
            Authentication auth = context.getAuthentication();
            if (auth == null) {
                respuesta = false;
            } else {
                Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
                respuesta = authorities.contains(new SimpleGrantedAuthority(role));
            }
        }

        return respuesta;
    }
}
