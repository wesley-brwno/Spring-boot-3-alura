package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas{
    private final MedicoRepository medicoRepository;

    public ValidadorMedicoAtivo(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public void validar(DadosAgendamentoConsulta dados) {

        if (dados.idMedico() == null) {
            return;
        }

        boolean isMedicoAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if (!isMedicoAtivo) {
            throw new ValidacaoException("Consulta não pode ser feita com médico inativo");
        }
    }
}
