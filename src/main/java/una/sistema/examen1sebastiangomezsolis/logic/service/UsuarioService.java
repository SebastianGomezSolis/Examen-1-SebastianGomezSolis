package una.sistema.examen1sebastiangomezsolis.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import una.sistema.examen1sebastiangomezsolis.data.UsuarioRepository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Usuario;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return usuarioRepository.findById(id.trim()).orElse(null);
    }
}
