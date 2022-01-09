package edu.viu.presentacionescontables.models.dao;

import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, String>{

	Usuario findByNombre(String nombre);
}
