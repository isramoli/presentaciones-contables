package edu.viu.presentacionescontables.usuarios.repository;

import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUsuarioRepository extends CrudRepository<Usuario, String> {

    @Query("Select u from Usuario u where u.tipoUsuario = 'ROLE_CUENTADANTE' ")
    List<Usuario> buscarUsuariosCuentadantes();
}
