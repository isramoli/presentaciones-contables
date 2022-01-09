package com.bolsadeideas.springboot.app.usuarios.repository;

import com.bolsadeideas.springboot.app.usuarios.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario, String> {
}
