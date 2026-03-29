package una.sistema.examen1sebastiangomezsolis.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Pacientemedicamento;

import java.util.List;

@Repository
public interface PacientemedicamentoRepository extends CrudRepository<Pacientemedicamento, Integer> {
    List<Pacientemedicamento> findByPacienteIdOrderByIdAsc(String pacienteId);
    List<Pacientemedicamento> findByPacienteIdAndMedicamentoId(String pacienteId, String medicamentoId);
}
