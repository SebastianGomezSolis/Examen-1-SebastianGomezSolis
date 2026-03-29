package una.sistema.examen1sebastiangomezsolis.presentation;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import una.sistema.examen1sebastiangomezsolis.logic.ModeloDatos;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Usuario;
import una.sistema.examen1sebastiangomezsolis.logic.service.PasswordHash;

@Controller
public class LoginController {
    @Autowired
    private ModeloDatos modeloDatos;

    @Autowired
    private PasswordHash passwordHash;

    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }

    @PostMapping("/ingresar")
    public String ingresar(@RequestParam("id") String id,
                           @RequestParam("clave") String clave,
                           Model model) {

        Usuario usuario = modeloDatos.getUsuarioService().buscarPorId(id);

        if (usuario != null) {
            if (passwordHash.verify(clave, usuario.getClave())) {
                session.setAttribute("usuario", usuario);
                return "redirect:/presentation/plan/show";
            }
        }

        model.addAttribute("error", "Usuario o clave incorrectos.");
        return "inicio";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
