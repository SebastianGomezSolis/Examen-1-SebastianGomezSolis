package una.sistema.examen1sebastiangomezsolis.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Medicamento;

@Repository
public interface MedicamentoRepository extends CrudRepository<Medicamento, String> {
}