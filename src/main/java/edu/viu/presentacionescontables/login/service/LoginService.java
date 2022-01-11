package edu.viu.presentacionescontables.login.service;

import java.util.ArrayList;
import java.util.List;

import edu.viu.presentacionescontables.config.RoleEnum;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;
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

import edu.viu.presentacionescontables._models.dao.IUsuarioDao;

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



}
