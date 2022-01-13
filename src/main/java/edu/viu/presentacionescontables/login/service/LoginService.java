package edu.viu.presentacionescontables.login.service;

import edu.viu.presentacionescontables.config.RoleEnum;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import edu.viu.presentacionescontables.usuarios.service.IUsuarioService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("jpaUserDetailsService")
public class LoginService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioService.buscarPorNombre(nombre);

        if (!usuario.isPresent()) {
            logger.error("Error en el Login: no existe el usuario '" + nombre + "' en el sistema!");
            throw new UsernameNotFoundException("Username: " + nombre + " no existe en el sistema!");
        } else {

            List<GrantedAuthority> authorities = new ArrayList<>();
            RoleEnum.obtenRolesHijos(usuario.get().getTipoUsuario()).forEach(rolHijo -> authorities.add(new SimpleGrantedAuthority(rolHijo)));

            if (authorities.isEmpty()) {
                logger.error("Error en el Login: Usuario '" + nombre + "' no tiene roles asignados!");
                throw new UsernameNotFoundException("Error en el Login: usuario '" + nombre + "' no tiene roles asignados!");
            }

            return new User(usuario.get().getNombre(), usuario.get().getPassword(), true, true, true, true, authorities);
        }

    }


}
