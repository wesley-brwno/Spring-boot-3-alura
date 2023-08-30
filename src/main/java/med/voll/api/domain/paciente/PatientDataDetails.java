package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record PatientDataDetails(
        Long id,
        String nome,
        String email,
        String telefone,
        String CPF,
        Endereco endereco
) {
    public PatientDataDetails(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCPF(), paciente.getEndereco());
    }
}
