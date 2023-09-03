package med.voll.api.domain.consulta;

public enum MotivoCancelamento {
    PACIENTE_DESISTIU("Paciente desistiu"),
    MEDICO_CANCELOU("Medico cancelou"),
    OUTRO("Outro");

    final String motivo;

    MotivoCancelamento(String motivo) {
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }
}
