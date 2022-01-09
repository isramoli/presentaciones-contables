package com.bolsadeideas.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;

import com.bolsadeideas.springboot.app.usuarios.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.usuarios.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByNombre(nombre);

        if (usuario == null) {
            logger.error("Error en el Login: no existe el usuario '" + nombre + "' en el sistema!");
            throw new UsernameNotFoundException("Username: " + nombre + " no existe en el sistema!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : usuario.getRoles()) {
            logger.info("Role: ".concat(role.getAuthority()));
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        if (authorities.isEmpty()) {
            logger.error("Error en el Login: Usuario '" + nombre + "' no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario '" + nombre + "' no tiene roles asignados!");
        }

        return new User(usuario.getNombre(), usuario.getPassword(), true, true, true, true, authorities);
    }

    private enum RoleEnum implements GrantedAuthority {

        ADMIN(Code.ROLE_ADMIN, Code.NAME_ADMIN),
        FISCAL_GENERAL(Code.ROLE_FISCAL_GENERAL, Code.NAME_FISCAL_GENERAL),
        FISCAL(Code.ROLE_FISCAL, Code.NAME_FISCAL),
        CUENTADANTE(Code.ROLE_CUENTADANTE, Code.NAME_CUENTADANTE);

        private final String role;
        private final String nombre;

        RoleEnum(String role, String nombre) {
            this.role = role;
            this.nombre = nombre;
        }

        @Override
        public String getAuthority() {
            return role;
        }

        public String getNombre() {
            return nombre;
        }

        public static class Code {
            public static final String ROLE_ADMIN = "ROLE_ADMIN";
            public static final String NAME_ADMIN = "Administador";

            public static final String ROLE_FISCAL_GENERAL = "ROLE_FISCAL_GENERAL";
            public static final String NAME_FISCAL_GENERAL = "Fiscal general";

            public static final String ROLE_FISCAL = "ROLE_FISCAL";
            public static final String NAME_FISCAL = "Fiscal";

            public static final String ROLE_CUENTADANTE = "ROLE_CUENTADANTE";
            public static final String NAME_CUENTADANTE = "Cuentadante";
        }
    }

}
