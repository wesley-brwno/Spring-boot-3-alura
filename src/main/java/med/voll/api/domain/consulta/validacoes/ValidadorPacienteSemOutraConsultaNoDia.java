package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsultas {
    private final ConsultaRepository repository;

    public ValidadorPacienteSemOutraConsultaNoDia(ConsultaRepository repository) {
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        Optional<LocalDateTime> agendaById = repository.findByPacienteId(dados.idPaciente());

        if (agendaById.isEmpty()) {
            return;
        }

        LocalDate consultaMarcada = agendaById.get().toLocalDate();
        LocalDate novaConsulta = dados.data().toLocalDate();

        if (novaConsulta.isEqual(consultaMarcada)) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
