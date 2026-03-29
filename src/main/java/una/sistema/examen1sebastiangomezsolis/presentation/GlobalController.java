package una.sistema.examen1sebastiangomezsolis.presentation;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import una.sistema.examen1sebastiangomezsolis.logic.ModeloDatos;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Farmacia;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Usuario;

@ControllerAdvice
public class GlobalController {
    @Autowired
    private HttpSession session;

    @Autowired
    private ModeloDatos modeloDatos;

    @ModelAttribute("usuarioActivo")
    public String usuarioActivo() {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        return usuario != null ? usuario.getId() : null;
    }

    @ModelAttribute("farmaciaActiva")
    public Farmacia farmaciaActiva() {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return null;
        }
        return modeloDatos.getFarmaciaService().buscarPorUsuario(usuario.getId());
    }
}