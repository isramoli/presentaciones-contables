package edu.viu.presentacionescontables.municipios.service;

import edu.viu.presentacionescontables.municipios.entity.Municipio;
import edu.viu.presentacionescontables.municipios.repository.IMunicipioRepository;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import edu.viu.presentacionescontables.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IMunicipioServiceImpl implements IMunicipioService {

    @Autowired
    private IMunicipioRepository municipioRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Municipio> buscarTodosMunicipios() {
        return (List<Municipio>) municipioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Municipio> buscarPorNombre(String nombre) {
        return municipioRepository.findById(nombre);
    }

    @Override
    public void guardar(Municipio municipio) {
        municipioRepository.save(municipio);
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