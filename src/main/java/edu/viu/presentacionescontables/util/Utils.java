package edu.viu.presentacionescontables.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class Utils {
    public static boolean hasRole(String role) {
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
