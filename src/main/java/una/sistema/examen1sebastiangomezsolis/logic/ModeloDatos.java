package una.sistema.examen1sebastiangomezsolis.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import una.sistema.examen1sebastiangomezsolis.logic.service.*;

@Component
public class ModeloDatos {
    @Autowired private UsuarioService usuarioService;
    @Autowired private FarmaciaService farmaciaService;
    @Autowired private MedicamentoService medicamentoService;
    @Autowired private PacienteService pacienteService;
    @Autowired private PacientemedicamentoService pacientemedicamentoService;

    public UsuarioService getUsuarioService() { return usuarioService; }
    public FarmaciaService getFarmaciaService() { return farmaciaService; }
    public MedicamentoService getMedicamentoService() { return medicamentoService; }
    public PacienteService getPacienteService() { return pacienteService; }
    public PacientemedicamentoService getPacientemedicamentoService() { return pacientemedicamentoService; }
}
