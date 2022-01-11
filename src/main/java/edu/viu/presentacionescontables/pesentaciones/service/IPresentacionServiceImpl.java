package edu.viu.presentacionescontables.pesentaciones.service;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import edu.viu.presentacionescontables.pesentaciones.repository.IPresentacionRepository;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import edu.viu.presentacionescontables.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IPresentacionServiceImpl implements IPresentacionService {

    @Autowired
    private IPresentacionRepository municipioRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Presentacion> findAll() {
        return (List<Presentacion>) municipioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Presentacion> buscarPorNombre(String nombre) {
        return municipioRepository.findById(nombre);
    }

    @Override
    public void guardar(Presentacion presentacion) {
        municipioRepository.save(presentacion);
    }

    @Override
    public void borrar(String nombre) {
        municipioRepository.deleteById(nombre);
    }

    @Override
    public List<Usuario> buscarUsuarios() {
        return usuarioService.buscarUsuariosCuentadantes();
    }
}
