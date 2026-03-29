package una.sistema.examen1sebastiangomezsolis.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import una.sistema.examen1sebastiangomezsolis.data.PacientemedicamentoRepository;
import una.sistema.examen1sebastiangomezsolis.logic.modelo.Pacientemedicamento;

import java.util.List;

@Service
public class PacientemedicamentoService {
    @Autowired
    private PacientemedicamentoRepository pacienteMedicamentoRepository;

    public List<Pacientemedicamento> listarPorPaciente(String pacienteId) {
        return pacienteMedicamentoRepository.findByPacienteIdOrderByIdAsc(pacienteId);
    }

    public Pacientemedicamento buscarPorPacienteYMedicamento(String pacienteId, String medicamentoId) {
        if (pacienteId == null || medicamentoId == null || pacienteId.isBlank() || medicamentoId.isBlank()) {
            return null;
        }
        List<Pacientemedicamento> resultados = pacienteMedicamentoRepository.findByPacienteIdAndMedicamentoId(pacienteId, medicamentoId);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Transactional
    public String registrarCompra(String pacienteId, String medicamentoId, Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            return "La cantidad de dosis debe ser mayor a cero.";
        }

        Pacientemedicamento pacienteMedicamento = buscarPorPacienteYMedicamento(pacienteId, medicamentoId);
        if (pacienteMedicamento == null) {
            return "El medicamento seleccionado no está asociado al paciente.";
        }

        int acumuladasActuales = pacienteMedicamento.getDosisafavor() == null ? 0 : pacienteMedicamento.getDosisafavor();
        pacienteMedicamento.setDosisafavor(acumuladasActuales + cantidad);
        pacienteMedicamentoRepository.save(pacienteMedicamento);
        return null;
    }

    @Transactional
    public String entregarRegalia(String pacienteId, String medicamentoId, Integer cantidadRegalias) {
        if (cantidadRegalias == null || cantidadRegalias <= 0) {
            return "La cantidad a entregar debe ser mayor a cero.";
        }

        Pacientemedicamento pacienteMedicamento = buscarPorPacienteYMedicamento(pacienteId, medicamentoId);
        if (pacienteMedicamento == null) {
            return "El medicamento seleccionado no está asociado al paciente.";
        }

        int acumuladasActuales = pacienteMedicamento.getDosisafavor() == null ? 0 : pacienteMedicamento.getDosisafavor();
        int consumoNecesario = pacienteMedicamento.getMedicamento().getPlan() * cantidadRegalias;
        
        if (acumuladasActuales < consumoNecesario) {
            return "No hay dosis suficientes a favor para entregar.";
        }

        pacienteMedicamento.setDosisafavor(acumuladasActuales - consumoNecesario);
        pacienteMedicamentoRepository.save(pacienteMedicamento);
        return null;
    }

    public int calcularRegalias(Pacientemedicamento pm) {
        if (pm == null || pm.getDosisafavor() == null || pm.getMedicamento() == null || 
            pm.getMedicamento().getPlan() == null || pm.getMedicamento().getPlan() == 0) {
            return 0;
        }
        return pm.getDosisafavor() / pm.getMedicamento().getPlan();
    }
}
