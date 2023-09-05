package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {

    private final PatientRepository repository;

    public ValidadorPacienteAtivo(PatientRepository repository) {
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        boolean isPatientAtivo = repository.findAtivoById(dados.idPaciente());
        if (!isPatientAtivo) {
            throw new ValidacaoException("Consulta não pode ser feita com paciente excluído");
        }
    }
}
