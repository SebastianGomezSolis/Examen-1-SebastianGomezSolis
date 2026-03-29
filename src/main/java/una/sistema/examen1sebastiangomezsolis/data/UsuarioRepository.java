package una.sistema.examen1sebastiangomezsolis.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
}
