package med.voll.api.domain.consulta;

import jakarta.validation.ValidationException;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PatientRepository patientRepository;

    public AgendaDeConsultas(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PatientRepository patientRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.patientRepository = patientRepository;
    }

    public void agendar(DadosAgendamentoConsulta dados) {
        if (!patientRepository.existsById(dados.idPaciente())) {
            throw new ValidationException("Id do paciente informado não existe");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidationException("Id do medico informado não existe");
        }

        Paciente paciente = patientRepository.getReferenceById(dados.idPaciente());
        Medico medico = escolherMedico(dados);


        Consulta consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidationException("Espeicalidae é obrigatíra quando médico nãofor escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(AppointmentCanceling dados) {
        if (!consultaRepository.existsById(dados.id())) {
            throw new ValidacaoException("Id da consulta informada não existe");
        }

        consultaRepository.deleteAppointmentIfBefore24Hours(dados.id());

        if (consultaRepository.existsById(dados.id())) {
            throw new ValidacaoException("A consulta com o ID "+dados.id()+" não foi cancelada pois não há atencedência de 24 horas.");
        }
    }
}
