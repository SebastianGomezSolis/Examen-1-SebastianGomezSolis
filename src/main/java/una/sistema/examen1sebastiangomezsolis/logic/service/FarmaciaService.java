package una.sistema.examen1sebastiangomezsolis.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import una.sistema.examen1sebastiangomezsolis.data.FarmaciaRepository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Farmacia;

@Service
public class FarmaciaService {
    @Autowired
    private FarmaciaRepository farmaciaRepository;

    public Farmacia buscarPorUsuario(String usuarioId) {
        if (usuarioId == null || usuarioId.isBlank()) {
            return null;
        }
        return farmaciaRepository.findByUsuarioId(usuarioId).orElse(null);
    }
}
