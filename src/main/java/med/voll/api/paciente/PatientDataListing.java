package med.voll.api.paciente;

public record PatientDataListing(
        String nome,
        String email,
        String CPF
) {
    public PatientDataListing (Paciente  paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCPF());
    }
}

