package una.sistema.examen1sebastiangomezsolis.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import una.sistema.examen1sebastiangomezsolis.data.PacienteRepository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Paciente;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return pacienteRepository.findById(id.trim()).orElse(null);
    }
}

