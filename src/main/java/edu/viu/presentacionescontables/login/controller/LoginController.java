package edu.viu.presentacionescontables.login.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para el login
 */
@Controller
public class LoginController {

    /**
     * Se encarga de recibir las peticiones de login
     * @return Devuelve la pantalla de login
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash) {

        if (principal != null) {
            flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
        }

        if (logout != null) {
            model.addAttribute("success", "Ha cerrado sesión con éxito!");
        }

        return "login";
    }

    @GetMapping({"/home", "/"})
    public String login2(Model model) {
        model.addAttribute("titulo", "Página principal");

        return "home";
    }
}
