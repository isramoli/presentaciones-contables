package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.usuarios.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, String>{

	Usuario findByNombre(String nombre);
}
