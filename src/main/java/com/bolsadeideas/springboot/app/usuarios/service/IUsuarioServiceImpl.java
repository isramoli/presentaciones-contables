package com.bolsadeideas.springboot.app.usuarios.service;

import com.bolsadeideas.springboot.app.usuarios.entity.Usuario;
import com.bolsadeideas.springboot.app.usuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IUsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.findById(nombre);
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) {
        Optional<Usuario> usuarioBBDD = usuarioRepository.findById(usuario.getNombre());
        if (usuarioBBDD.isPresent()) {
//            usuarioBBDD.get().set
        }
    }

    @Override
    public void borrar(String nombre) {
        usuarioRepository.deleteById(nombre);
    }
}
