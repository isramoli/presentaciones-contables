package com.bolsadeideas.springboot.app.login.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class LoginService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

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
        RoleEnum.obtenRolesHijos(usuario.getTipoUsuario()).forEach(rolHijo -> authorities.add(new SimpleGrantedAuthority(rolHijo)));

        if (authorities.isEmpty()) {
            logger.error("Error en el Login: Usuario '" + nombre + "' no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario '" + nombre + "' no tiene roles asignados!");
        }

        return new User(usuario.getNombre(), usuario.getPassword(), true, true, true, true, authorities);
    }

    public enum RoleEnum implements GrantedAuthority {

        ROLE_ADMIN(Code.ROLE_ADMIN, Code.VALOR_ADMIN, Code.NAME_ADMIN),
        ROLE_FISCAL_GENERAL(Code.ROLE_FISCAL_GENERAL, Code.VALOR_FISCAL_GENERAL, Code.NAME_FISCAL_GENERAL),
        ROLE_FISCAL(Code.ROLE_FISCAL, Code.VALOR_FISCAL, Code.NAME_FISCAL),
        ROLE_CUENTADANTE(Code.ROLE_CUENTADANTE, Code.VALOR_CUENTADANTE, Code.NAME_CUENTADANTE);

        private final String role;
        private final int valor;
        private final String nombre;

        RoleEnum(String role, int valor, String nombre) {
            this.role = role;
            this.valor = valor;
            this.nombre = nombre;
        }

        @Override
        public String getAuthority() {
            return role;
        }

        public String getNombre() {
            return nombre;
        }

        public int getValor() {
            return valor;
        }

        public static List<String> obtenRolesHijos(String role) {
            List<String> respuesta;
            Optional<RoleEnum> roleBuscado = Stream.of(RoleEnum.values()).filter(roleEnum -> roleEnum.role.equals(role)).findAny();
            if (roleBuscado.isPresent()) {
                respuesta = Stream.of(RoleEnum.values()).filter(roleEnum -> roleEnum.valor >= roleBuscado.get().getValor()).map(RoleEnum::getAuthority).collect(Collectors.toList());
            } else {
                respuesta = new ArrayList<>();
            }
            return respuesta;
        }

        public static Map<String, String> obtenerTodosRoles() {
            return Stream.of(RoleEnum.values()).collect(Collectors.toMap(RoleEnum::getAuthority, RoleEnum::getNombre, (s, s2) -> s, LinkedHashMap::new));
        }

        public static class Code {
            public static final String ROLE_ADMIN = "ROLE_ADMIN";
            public static final String NAME_ADMIN = "Administador";
            public static final int VALOR_ADMIN = 0;

            public static final String ROLE_FISCAL_GENERAL = "ROLE_FISCAL_GENERAL";
            public static final String NAME_FISCAL_GENERAL = "Fiscal general";
            public static final int VALOR_FISCAL_GENERAL = 1;

            public static final String ROLE_FISCAL = "ROLE_FISCAL";
            public static final String NAME_FISCAL = "Fiscal";
            public static final int VALOR_FISCAL = 2;

            public static final String ROLE_CUENTADANTE = "ROLE_CUENTADANTE";
            public static final String NAME_CUENTADANTE = "Cuentadante";
            public static final int VALOR_CUENTADANTE = 3;
        }
    }

}
