package una.sistema.examen1sebastiangomezsolis.presentation;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import una.sistema.examen1sebastiangomezsolis.logic.ModeloDatos;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Paciente;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Usuario;

import java.util.Collections;

@Controller
@RequestMapping("/presentation/plan")
public class InicioController {
    @Autowired
    private ModeloDatos modeloDatos;

    @Autowired
    private HttpSession session;

    @GetMapping("/show")
    public String show(@RequestParam(name = "pacienteId", required = false) String pacienteId, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        cargarDatosPaciente(model, pacienteId);
        return "show";
    }

    @PostMapping("/medicamento/procesar")
    public String procesarMedicamento(@RequestParam("pacienteId") String pacienteId,
                                      @RequestParam("medicamentoId") String medicamentoId,
                                      @RequestParam("cantidad") Integer cantidad,
                                      @RequestParam("accion") String accion,
                                      Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        if (accion.equals("REGISTRAR")) {
            String msg = modeloDatos.getPacientemedicamentoService().registrarCompra(pacienteId, medicamentoId, cantidad);
            if (msg != null) {
                model.addAttribute("error", msg);
            } else {
                model.addAttribute("mensaje", "Compra registrada correctamente.");
            }
        } 
        
        if (accion.equals("ENTREGAR")) {
            String msg = modeloDatos.getPacientemedicamentoService().entregarRegalia(pacienteId, medicamentoId, cantidad);
            if (msg != null) {
                model.addAttribute("error", msg);
            } else {
                model.addAttribute("mensaje", "Regalía entregada correctamente.");
            }
        }
        
        cargarDatosPaciente(model, pacienteId);
        return "show";
    }

    private void cargarDatosPaciente(Model model, String pacienteId) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("farmaciaActiva", modeloDatos.getFarmaciaService().buscarPorUsuario(usuario.getId()));
        }
        
        if (pacienteId == null || pacienteId.isBlank()) {
            model.addAttribute("pacienteId", "");
            model.addAttribute("pacienteNombre", "No se ha seleccionado un paciente");
            model.addAttribute("medicamentosPaciente", Collections.emptyList());
            return;
        }

        model.addAttribute("pacienteId", pacienteId);
        Paciente paciente = modeloDatos.getPacienteService().buscarPorId(pacienteId.trim());
        
        if (paciente == null) {
            model.addAttribute("pacienteNombre", "Paciente no encontrado");
            model.addAttribute("medicamentosPaciente", Collections.emptyList());
        } else {
            model.addAttribute("pacienteNombre", paciente.getNombre());
            model.addAttribute("paciente", paciente);
            model.addAttribute("medicamentosPaciente", modeloDatos.getPacientemedicamentoService().listarPorPaciente(paciente.getId()));
            model.addAttribute("pmService", modeloDatos.getPacientemedicamentoService());
        }
    }
}
