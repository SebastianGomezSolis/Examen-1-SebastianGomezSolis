package una.sistema.examen1sebastiangomezsolis.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Farmacia;

import java.util.Optional;

@Repository
public interface FarmaciaRepository extends CrudRepository<Farmacia, String> {
    Optional<Farmacia> findByUsuarioId(String usuarioId);
}


