package una.sistema.examen1sebastiangomezsolis.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import una.sistema.examen1sebastiangomezsolis.data.MedicamentoRepository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Medicamento;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public Medicamento buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return medicamentoRepository.findById(id).orElse(null);
    }
}
